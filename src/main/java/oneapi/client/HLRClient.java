package oneapi.client;

import java.util.List;
import oneapi.listener.HLRNotificationsListener;
import oneapi.listener.ResponseListener;
import oneapi.model.RoamingNotification;
import oneapi.model.SubscribeToHLRDeliveryNotificationsRequest;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.Roaming;


public interface HLRClient {

	/**
	 * Query the customer’s roaming status for a single network-connected mobile device and get HLR to the specified notify url
	 * @param address (mandatory) mobile device number being queried
	 * @param notifyURL (mandatory) URL to receive the roaming status asynchronously
	 * @param clientCorrelator (optional) Active only if notifyURL is specified, otherwise ignored. Uniquely identifies this request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request helps the operator to avoid call the same request twice.
	 * @param callbackData (optional) Active only if notifyURL is specified, otherwise ignored. This is custom data to pass back in notification to notifyURL, so you can use it to identify the request or any other useful data, such as a function name.
	 */
	void queryHLR(String address, String notifyURL, String clientCorrelator, String callbackData);

	/**
	 * Query the customer’s roaming status for a single network-connected mobile device and get HLR to the specified notify url
	 * @param address (mandatory) mobile device number being queried
	 * @param notifyURL (mandatory) URL to receive the roaming status asynchronously
	 */
	void queryHLR(String address, String notifyURL);

	/**
	 * Query the customer’s roaming status for a single network-connected mobile device and get HLR as the response
	 * @param address (mandatory) mobile device number being queried
	 * @return Roaming
	 */
	Roaming queryHLR(String address);

	/**
	 * Query asynchronously the customer’s roaming status for a single network-connected mobile device and get HLR as the response
	 * @param address (mandatory) mobile device number being queried
	 * @param responseListener (mandatory) method to call after receiving HLR response
	 */
	 void queryHLRAsync(String address, ResponseListener<Roaming> responseListener);

	/**
	 * Convert JSON to HLR Notification </summary>
	 * @param json
	 * @return RoamingNotification
	 */
	RoamingNotification convertJsonToHLRNotificationExample(String json);

	/**
	 * Start subscribing to HLR delivery notifications over OneAPI         
	 * @param subscribeToHLRDeliveryNotificationsRequest
	 * @return  String subscriptionId
	 */
	String subscribeToHLRDeliveryNotifications(SubscribeToHLRDeliveryNotificationsRequest subscribeToHLRDeliveryNotificationsRequest);

	/**
	 * Get HLR delivery notifications subscriptions by subscription id
	 * @param subscriptionId
	 * @return DeliveryReportSubscription[]
	 */
	DeliveryReportSubscription[] getHLRDeliveryNotificationsSubscriptionsById(String subscriptionId);

	/**
	 * Stop subscribing to HLR delivery notifications over OneAPI 
	 * @param subscriptionId (mandatory) contains the subscriptionId of a previously created HLR delivery receipt subscription
	 * return ResponseCode (integer)
	 */
	void removeHLRDeliveryNotificationsSubscription(String subscriptionId);

	/**
	 * Add OneAPI PUSH 'HLR' Notifications listener and start push server simulator
	 * @param listener
	 */
	void addPushHLRNotificationsListener(HLRNotificationsListener listener);

	/**
	 * Returns HLR Notifications PUSH Listeners list
	 * @return List<HLRNotificationsListener>
	 */
	List<HLRNotificationsListener> getHLRPushNotificationListeners();

	/**
	 *  Remove PUSH HLR listeners and stop server
	 */
	void removePushHLRNotificationsListeners();

}