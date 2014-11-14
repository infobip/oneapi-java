package oneapi.client;

import java.util.List;
import oneapi.listener.DeliveryReportListener;
import oneapi.listener.DeliveryStatusNotificationsListener;
import oneapi.listener.InboundMessageListener;
import oneapi.listener.InboundMessageNotificationsListener;
import oneapi.listener.ResponseListener;
import oneapi.model.*;
import oneapi.model.common.DeliveryInfoList;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.InboundSMSMessageList;
import oneapi.model.common.MoSubscription;


public interface SMSMessagingClient {

	/**
	 * Send an SMS to one or more mobile terminals using the customized 'SMSRequest' object 
	 * @param sms - object containing data needed to be filled in order to send the SMS
	 * @return String Request Id
	 */
	SendMessageResult sendSMS(SMSRequest sms);

	/**
     * Send an SMS asynchronously over OneAPI to one or more mobile terminals using the customized 'SMSRequest' object
     * @param smsRequest (mandatory) object containing data needed to be filled in order to send the SMS
     * @param responseListener (mandatory) method to call after receiving sent SMS response
     */   
    void sendSMSAsync(SMSRequest smsRequest, final ResponseListener<SendMessageResult> responseListener);
	
	/**
	 * Query the delivery status for an SMS sent to one or more mobile terminals                        
	 * @param senderAddress (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
	 * @param requestId (mandatory) contains the requestId returned from a previous call to the sendSMS function 
	 * @return DeliveryInfoList
	 */
	DeliveryInfoList queryDeliveryStatus(String senderAddress, String requestId);
		
	/**
     * Query the delivery status asynchronously over OneAPI for an SMS sent to one or more mobile terminals
     * @param senderAddress (mandatory) is the address from which SMS messages are being sent. Do not URL encode this value prior to passing to this function
     * @param requestId (mandatory) contains the requestId returned from a previous call to the sendSMS function
     * @param responseListener (mandatory) method to call after receiving delivery status
     */
	 void queryDeliveryStatusAsync(String senderAddress, String requestId, ResponseListener<DeliveryInfoList> responseListener);
	
	 /**
     * Convert JSON to Delivery Info Notification
     * @return DeliveryInfoNotification
     */
    DeliveryInfoNotification convertJsonToDeliveryInfoNotification(String json);

	/**
	 * Start subscribing to delivery status notifications over OneAPI for all your sent SMS  	                          
	 * @return String Subscription Id
	 */
	String subscribeToDeliveryStatusNotifications(SubscribeToDeliveryNotificationsRequest subscribeToDeliveryNotificationsRequest);

	/**
	 * Retrieve delivery notifications subscriptions by sender address
	 * @return DeliveryReportSubscription[]
	 */
	DeliveryReportSubscription[] getDeliveryNotificationsSubscriptionsBySender(String senderAddress);

	/**
	 * Retrieve delivery notifications subscriptions by subscription id
	 * @return DeliveryReportSubscription
	 */
	DeliveryReportSubscription getDeliveryNotificationsSubscriptionById(String subscriptionId);

	/**
	 * Retrieve delivery notifications subscriptions by for the current user
	 * @return DeliveryReportSubscription[]
	 */
	DeliveryReportSubscription[] getDeliveryNotificationsSubscriptions();

	/**
	 * Stop subscribing to delivery status notifications for all your sent SMS  
	 * @param subscriptionId (mandatory) contains the subscriptionId of a previously created SMS delivery receipt subscription
	 */
	void removeDeliveryNotificationsSubscription(String subscriptionId);

	/**
	 * Retrieve SMS messages sent to your Web application over OneAPI
	 * @return InboundSMSMessageList
	 */
	InboundSMSMessageList getInboundMessages();

	/**
	 * Retrieve SMS messages sent to your Web application 
	 * @param  maxBatchSize (mandatory) is the maximum number of messages to get in this request
	 * @return InboundSMSMessageList
	 */
	InboundSMSMessageList getInboundMessages(int maxBatchSize);
	
	 /**
     * Get asynchronously SMS messages sent to your Web application over OneAPI
     * @param responseListener (mandatory) method to call after receiving inbound messages
     */
    void getInboundMessagesAsync(final ResponseListener<InboundSMSMessageList> responseListener);
	
    /**
     * Get asynchronously SMS messages sent to your Web application over OneAPI
     * @param maxBatchSize (optional) is the maximum number of messages to get in this request
     * @param responseListener (mandatory) method to call after receiving inbound messages
     */
    void getInboundMessagesAsync(int maxBatchSize, ResponseListener<InboundSMSMessageList> responseListener);
    
	/**
     * Convert JSON to Inbound SMS Message Notification
     * @return InboundSMSMessageList
     */
    InboundSMSMessageList convertJsonToInboundSMSMessageNotificationExample(String json);
	
	/**
	 * Start subscribing to notifications of SMS messages sent to your application over OneAPI                           
	 * @param subscribeToInboundMessagesRequest (mandatory) contains inbound messages subscription data
	 * @return string - Subscription Id 
	 */
    String subscribeToInboundMessagesNotifications(SubscribeToInboundMessagesRequest subscribeToInboundMessagesRequest);

	 /**
     * Retrieve inbound messages notifications subscriptions for the current user
     * @return MoSubscription[]
     */
    MoSubscription[] getInboundMessagesNotificationsSubscriptions(int page, int pageSize);
    
    
    /**
     * Retrieve inbound messages notifications subscriptions for the current user (Default values are used: page=1, pageSize=10)
     * @return MoSubscription[]
     */
    MoSubscription[] getInboundMessagesNotificationsSubscriptions();
	
	/**
	 * Stop subscribing to message receipt notifications for all your received SMS                       
	 * @param subscriptionId (mandatory) contains the subscriptionId of a previously created SMS message receipt subscription
	 */
    void removeInboundMessagesSubscription(String subscriptionId);

    /**
     * Get MO Number Types
     */
    MoNumberType[] getMoNumberTypes();

    /**
     * Retrieve delivery reports
     * @return DeliveryReportList
     */
	DeliveryReportList getDeliveryReports(int limit);
	
    /**
     * Get delivery reports asynchronously
     * @param responseListener (mandatory) method to call after receiving delivery reports
     */
    void getDeliveryReportsAsync(int limit, ResponseListener<DeliveryReportList> responseListener);
    
	/**
	 * Retrieve delivery reports 
	 * @return DeliveryReportList
	 */
	DeliveryReportList getDeliveryReports();
	
	/**
     * Get delivery reports asynchronously
     * @param responseListener (mandatory) method to call after receiving delivery reports
     */
    void getDeliveryReportsAsync(ResponseListener<DeliveryReportList> responseListener);

	 /**
     * Retrieve delivery reports by Request Id
     * @return DeliveryReportList
     */
    DeliveryReportList getDeliveryReportsByRequestId(String requestId, int limit);
	
	/**
	 * Retrieve delivery reports by Request Id
	 * @return DeliveryReportList
	 */
    DeliveryReportList getDeliveryReportsByRequestId(String requestId);
 
	/**
	 * Add 'INBOUND Messages' listener
	 * 
	 * @param listener - (new InboundMessageListener)
	 */
	void addPullInboundMessageListener(InboundMessageListener listener);

	/**
	 * Add 'Delivery Reports' listener.
	 * @param listener - (new DeliveryReportListener)
	 */
	void addPullDeliveryReportListener(DeliveryReportListener listener);

	/**
	 * Returns INBOUND Message Listeners list
	 */
	List<InboundMessageListener> getInboundMessagePullListeners();

	/**
	 * Returns Delivery Reports Listeners list
	 */
	List<DeliveryReportListener> getDeliveryReportPullListeners();
	
	/**
	 * Remove Delivery Reports listeners and stop retriever
	 */

	void removePullDeliveryReportListeners();
	
	/**
	 * Remove INBOUND Messages listeners and stop retriever
	 */
	void removePullInboundMessageListeners();
	
	 /**
     * Add OneAPI PUSH 'Delivery Status' Notifications listener  and start push server simulator
     */
    void addPushDeliveryStatusNotificationListener(DeliveryStatusNotificationsListener listener);
    
    /**
     * Add OneAPI PUSH 'INBOUND Messages' Notifications listener and start push server simulator
     */
    void addPushInboundMessageListener(InboundMessageNotificationsListener listener);
    
    /**
     * Returns Delivery Status Notifications PUSH Listeners list 
     */
    List<DeliveryStatusNotificationsListener> getDeliveryStatusNotificationPushListeners();
    
    /**
     * Returns INBOUND Message Notifications PUSH Listeners list
     */
    List<InboundMessageNotificationsListener> getInboundMessagePushListeners();
    
    /**
     *  Remove PUSH Delivery Reports Notifications listeners and stop server
     */
    void removePushDeliveryStatusNotificationListeners();
    
    /**
     *  Remove PUSH Delivery Reports Notifications listeners and stop server
     */
    void removePushInboundMessageListeners();
}
