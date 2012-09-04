package oneapi.listener;

import java.util.EventListener;

import oneapi.model.common.InboundSMSMessageList;

public interface InboundMessageNotificationsListener extends EventListener {
	public void onMessageReceived(InboundSMSMessageList smsMessageList);
}
