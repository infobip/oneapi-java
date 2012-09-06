package oneapi.retriever;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import oneapi.client.impl.SMSMessagingClientImpl;
import oneapi.exception.RequestException;
import oneapi.listener.DeliveryReportListener;
import oneapi.model.DeliveryReportList;


public class DeliveryReportRetriever {
	private ScheduledExecutorService fScheduler;

	public void start(long interval, SMSMessagingClientImpl smsMessagingImpl) {
		this.stop();

		if (interval <= 0)
			return;

		fScheduler = Executors.newScheduledThreadPool(1);

		// fire first dlr pull attempt after 2 sec and then each interval milliseconds
		Runnable poller = new PollerTask(smsMessagingImpl);
		fScheduler.scheduleWithFixedDelay(poller, 2000, interval, TimeUnit.MILLISECONDS);
	}

	public void stop() {
		if (fScheduler != null) {
			fScheduler.shutdown();
		}
	}

	private static final class PollerTask implements Runnable {
		private SMSMessagingClientImpl smsMessagingImpl;
	
		public PollerTask(SMSMessagingClientImpl smsMessagingImpl) {
			this.smsMessagingImpl = smsMessagingImpl;		
		}
		public void run() {	
			List<DeliveryReportListener> dlrStatusListeners = this.smsMessagingImpl.getDeliveryReportPullListeners();

			if ((dlrStatusListeners != null) && (dlrStatusListeners.size() > 0)) {
				// use pull method to gather delivery reports 
				DeliveryReportList deliveryReportList = null;;
				Throwable error = null;
				try {
					deliveryReportList = smsMessagingImpl.getDeliveryReports();
				} catch (RequestException e) {
					error = e.getCause();
				}
						
				if ((deliveryReportList != null && deliveryReportList.getDeliveryReports() != null && deliveryReportList.getDeliveryReports().length > 0) || (error != null)) {
					this.fireReportRetrieved(deliveryReportList, dlrStatusListeners, error);		
				}
			}
		}

		private void fireReportRetrieved(DeliveryReportList deliveryReportList, List<DeliveryReportListener> listeners, Throwable error) {
			for (int i=0; i<listeners.size(); i++) {				
				listeners.get(i).onDeliveryReportReceived(deliveryReportList, error);		
			}
		}
	}
}
