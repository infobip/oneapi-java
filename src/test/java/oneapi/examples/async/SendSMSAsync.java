package oneapi.examples.async;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import oneapi.PropertyLoader;
import oneapi.model.Language;
import oneapi.model.LanguageCode;
import org.apache.log4j.BasicConfigurator;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.listener.ResponseListener;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;

/**
 * To run this example follow these 3 steps:
 * <p/>
 * 1.) Download 'OneApi Java library' - available at github.com/infobip
 * <p/>
 * 2.) Open 'examples.SendSMSAsync' class to edit where you should populate the following fields:
 * 'USERNAME'		'DESTINATION'
 * 'PASSWORD'      'SENDER'
 * <p/>
 * 3.) Run the example class by right click it and select 'Run As -> Java Application'
 */

public class SendSMSAsync {

    // ----------------------------------------------------------------------------------------------------
    // TODO: Fill you own values here or create/change the example.properties file:
    // ----------------------------------------------------------------------------------------------------

    private static final String USERNAME = PropertyLoader.loadProperty("example.properties", "username");
    private static final String PASSWORD = PropertyLoader.loadProperty("example.properties", "password");
    private static String SENDER = PropertyLoader.loadProperty("example.properties", "sender");
    private static final String DESTINATION = PropertyLoader.loadProperty("example.properties", "destination");
    private static final String MESSAGE = PropertyLoader.loadProperty("example.properties", "message");

    public static void main(String[] args) {

        // Configure logger
        BasicConfigurator.configure();

        Configuration configuration = new Configuration(USERNAME, PASSWORD);
        SMSClient smsClient = new SMSClient(configuration);

        SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);

        smsClient.getSMSMessagingClient().sendSMSAsync(smsRequest, new ResponseListener<SendMessageResult>() {

            @Override
            public void onGotResponse(SendMessageResult sendMessageResult, Throwable error) {
                if (error == null) {
                    System.out.println(sendMessageResult);
                } else {
                    System.out.println(error.getMessage());
                }
            }
        });
    }
}

