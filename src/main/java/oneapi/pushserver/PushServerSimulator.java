package oneapi.pushserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import oneapi.client.impl.HLRClientImpl;
import oneapi.client.impl.SMSMessagingClientImpl;
import oneapi.model.DeliveryInfoNotification;
import oneapi.model.RoamingNotification;
import oneapi.model.common.InboundSMSMessageList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PushServerSimulator {
	protected static final Logger LOGGER = LoggerFactory.getLogger(PushServerSimulator.class);
	private ServerSocket server;
	private SMSMessagingClientImpl smsMessagingImpl = null;
	private HLRClientImpl hlrClientImpl = null;	
	protected int port;
	private boolean running = false;

	public PushServerSimulator(SMSMessagingClientImpl smsMessagingImpl, int port) {
		this.smsMessagingImpl = smsMessagingImpl;
		this.port= port;
	}

	public PushServerSimulator(HLRClientImpl hlrClientImpl, int port)
	{
		this.hlrClientImpl = hlrClientImpl;
		this.port= port;	
	}

	public void start() {
		if (!running) {		
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						if ( server == null) {							
							server = new ServerSocket( port );	
							running = true;
							if (LOGGER.isInfoEnabled())
							{
								LOGGER.info("Push Server Simulator is successfully started on port " + port);
							}
						}
					}
					catch ( IOException e ) {
						if (LOGGER.isErrorEnabled()) {
							LOGGER.error("Error occured while trying to start Push Server Simulator on port " + String.valueOf(port) + ". Message: " + e.getMessage());
						}
						running = false;
					}

					while ( running ) {
						Socket connection = null;
						try {
							connection = server.accept();

							// get request from client
							String request = getRequest( connection );

							// get content (POST request)
							int postlen = parseContentLength(request);
							BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

							char[] posted = new char[postlen];
							in.read(posted, 0, postlen);
							String postRequest = new String(posted);
							in.close();

							processRequestData(postRequest);												
						}
						catch ( IOException ex ) {
							if (LOGGER.isErrorEnabled()) {
								LOGGER.error("Error occured. Message: " + ex.getMessage());
							}		
						}
						finally {
							if ( connection != null ) {
								try {
									connection.close();
								} catch (IOException e) {
									if (LOGGER.isErrorEnabled()) {
										LOGGER.error("Error occured while trying to close Push Server Simulator connection on port " + String.valueOf(port) + ". Message: " + e.getMessage());
									}
								}
							}
						}
					}

					try {	
						if ( server != null && server.isClosed() == false ) {
							server.close();		
							if (LOGGER.isInfoEnabled()) {
								LOGGER.info("Push Server Simulator on port " + String.valueOf(port) + " is successfully stopped.");
							}
						}
					}
					catch ( IOException e ) {
						if (LOGGER.isErrorEnabled()) {
							LOGGER.error("Error occured while trying to stop Push Server Simulator on port " + String.valueOf(port) + ". Message: " + e.getMessage());
						}
					}

				}
			});

			t.start();	
		}
	}

	private void processRequestData(String json) {
		if (json.trim().length() != 0)
		{
			if (smsMessagingImpl != null)
			{
				if (json.contains("deliveryInfoNotification"))
				{
					DeliveryInfoNotification deliveryInfoNotification = smsMessagingImpl.convertJsonToDeliveryInfoNotification(json);
					if (smsMessagingImpl.getDeliveryStatusNotificationPushListeners() != null)
					{
						for (int i = 0; i < smsMessagingImpl.getDeliveryStatusNotificationPushListeners().size(); i++)
						{
							smsMessagingImpl.getDeliveryStatusNotificationPushListeners().get(i).onDeliveryStatusNotificationReceived(deliveryInfoNotification);
						}
					}
				}
				else if (json.contains("inboundSMSMessage"))
				{
					if (smsMessagingImpl != null)
					{
						if (smsMessagingImpl.getInboundMessagePushListeners()  != null)
						{
							InboundSMSMessageList smsMessagesList = smsMessagingImpl.convertJsonToInboundSMSMessageNotificationExample(json);
							for (int i = 0; i < smsMessagingImpl.getInboundMessagePushListeners().size(); i++)
							{
								smsMessagingImpl.getInboundMessagePushListeners().get(i).onMessageReceived(smsMessagesList);
							}
						}
					}
				}
			}
			else if (hlrClientImpl != null)
			{
				if (json.contains("terminalRoamingStatusList"))
				{
					if (hlrClientImpl.getHLRPushNotificationListeners() != null)
					{
						RoamingNotification roamingNotification = hlrClientImpl.convertJsonToHLRNotificationExample(json);
						for (int i = 0; i < hlrClientImpl.getHLRPushNotificationListeners().size(); i++)
						{
							hlrClientImpl.getHLRPushNotificationListeners().get(i).OnHLRReceived(roamingNotification);
						}
					}
				}
			}
		}
	}

	private int parseContentLength(String request) {
		int position = request.indexOf("Content-Length:");
		if ( position == -1) {
			return 0;
		}
		String contentLength = request.substring(position);
		contentLength = contentLength.split(" ")[1];
		contentLength = contentLength.split("\r\n")[0];
		return Integer.valueOf(contentLength);
	}

	private String getRequest( Socket connection ) throws IOException {
		InputStream in = connection.getInputStream();
		StringBuilder request = new StringBuilder();
		int i;
		while( ( i = in.read() ) != -1 ) {
			request.append( (char) i );
			if( request.toString().endsWith( "\r\n\r\n" ) || request.toString().endsWith( "\n\n" ) ) {
				return request.toString();
			}
		}
		return request.toString();
	}

	public void stop() {	
		running = false;	
	}
}
