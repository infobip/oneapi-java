package oneapi.listener;

import java.util.EventListener;

import oneapi.model.common.InboundSMSMessageList;

public interface InboundMessageListener extends EventListener {
	public void onMessageRetrieved(InboundSMSMessageList inboundSMSMessageList, Throwable error);
}
