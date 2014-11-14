package oneapi.client.impl;

import oneapi.client.HLRClient;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.listener.HLRNotificationsListener;
import oneapi.listener.ResponseListener;
import oneapi.model.RequestData;
import oneapi.model.RequestData.Method;
import oneapi.model.RoamingNotification;
import oneapi.model.SubscribeToHLRDeliveryNotificationsRequest;
import oneapi.model.common.DeliveryReceiptSubscription;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.Roaming;
import oneapi.pushserver.PushServerSimulator;

import java.util.ArrayList;
import java.util.List;

public class HLRClientImpl extends OneAPIBaseClientImpl implements HLRClient {
    private static final String HLR_URL_BASE = "/terminalstatus/queries";
    private static final String HLR_SUBSCRIPTION_URL_BASE = "/smsmessaging/hlr/subscriptions";

    private volatile List<HLRNotificationsListener> hlrPushListenerList = null;
    private PushServerSimulator hlrPushServerSimulator;

    public HLRClientImpl(Configuration configuration) {
        super(configuration);
    }

    /**
     * Query the customer’s roaming status for a single network-connected mobile device and get HLR to the specified notify url
     *
     * @param address          (mandatory) mobile device number being queried
     * @param notifyURL        (mandatory) URL to receive the roaming status asynchronously
     * @param clientCorrelator (optional) Active only if notifyURL is specified, otherwise ignored. Uniquely identifies this request. If there is a communication failure during the request, using the same clientCorrelator when retrying the request helps the operator to avoid call the same request twice.
     * @param callbackData     (optional) Active only if notifyURL is specified, otherwise ignored. This is custom data to pass back in notification to notifyURL, so you can use it to identify the request or any other useful data, such as a function name.
     */
    @Override
    public void queryHLR(String address, String notifyURL, String clientCorrelator, String callbackData) {
        if (notifyURL == null || notifyURL.length() == 0) {
            throw new RequestException("'notifiyURL' parmeter is mandatory.");
        }

        StringBuilder urlBuilder = new StringBuilder(HLR_URL_BASE);
        urlBuilder.append("/roamingStatus?address=");
        urlBuilder.append(encodeURLParam(address));
        urlBuilder.append("&includeExtendedData=true");
        urlBuilder.append("&notifyURL=");
        urlBuilder.append(encodeURLParam(notifyURL));

        if (clientCorrelator != null && clientCorrelator.length() > 0) {
            urlBuilder.append("&clientCorrelator=");
            urlBuilder.append(encodeURLParam(clientCorrelator));
        }

        if (callbackData != null && callbackData.length() > 0) {
            urlBuilder.append("&callbackData=");
            urlBuilder.append(encodeURLParam(callbackData));
        }

        RequestData requestData = new RequestData(urlBuilder.toString(), Method.GET);
        executeMethod(requestData);
    }

    /**
     * Query the customer’s roaming status for a single network-connected mobile device and get HLR to the specified notify url
     *
     * @param address   (mandatory) mobile device number being queried
     * @param notifyURL (mandatory) URL to receive the roaming status asynchronously
     */
    @Override
    public void queryHLR(String address, String notifyURL) {
        queryHLR(address, notifyURL, null, null);
    }

    /**
     * Query the customer’s roaming status for a single network-connected mobile device and get HLR as the response
     *
     * @param address (mandatory) mobile device number being queried
     * @return Roaming
     */
    @Override
    public Roaming queryHLR(String address) {
        RequestData requestData = new RequestData(HLR_URL_BASE + "/roamingStatus?address=" + encodeURLParam(address) + "&includeExtendedData=true", Method.GET, "roaming");
        return executeMethod(requestData, Roaming.class);
    }

    /**
     * Query asynchronously the customer’s roaming status for a single network-connected mobile device and get HLR as the response
     *
     * @param address          (mandatory) mobile device number being queried
     * @param responseListener (mandatory) method to call after receiving HLR response
     */
    @Override
    public void queryHLRAsync(String address, final ResponseListener<Roaming> responseListener) {
        RequestData requestData = new RequestData(HLR_URL_BASE + "/roamingStatus?address=" + encodeURLParam(address) + "&includeExtendedData=true", Method.GET, "roaming");
        executeMethodAsync(requestData, Roaming.class, responseListener);
    }

    /**
     * Convert JSON to HLR Notification </summary>
     *
     * @param json
     * @return RoamingNotification
     */
    public RoamingNotification convertJsonToHLRNotificationExample(String json) {
        return convertJSONToObject(json.getBytes(), RoamingNotification.class, "terminalRoamingStatusList");
    }

    /**
     * Start subscribing to HLR delivery notifications over OneAPI
     *
     * @param subscribeToHLRDeliveryNotificationsRequest
     * @return String subscriptionId
     */
    @Override
    public String subscribeToHLRDeliveryNotifications(SubscribeToHLRDeliveryNotificationsRequest subscribeToHLRDeliveryNotificationsRequest) {
        RequestData requestData = new RequestData(HLR_SUBSCRIPTION_URL_BASE, Method.POST, "deliveryReceiptSubscription", subscribeToHLRDeliveryNotificationsRequest, JSON_CONTENT_TYPE);
        DeliveryReceiptSubscription deliveryReceiptSubscription = executeMethod(requestData, DeliveryReceiptSubscription.class);
        return getIdFromResourceUrl(deliveryReceiptSubscription.getResourceURL());
    }

    /**
     * Get HLR delivery notifications subscriptions by subscription id
     *
     * @param subscriptionId
     * @return DeliveryReportSubscription[]
     */
    @Override
    public DeliveryReportSubscription[] getHLRDeliveryNotificationsSubscriptionsById(String subscriptionId) {
        RequestData requestData = new RequestData(HLR_SUBSCRIPTION_URL_BASE + "/" + encodeURLParam(subscriptionId), Method.GET, "deliveryReceiptSubscriptions");
        return executeMethod(requestData, DeliveryReportSubscription[].class);
    }

    /**
     * Stop subscribing to HLR delivery notifications over OneAPI
     *
     * @param subscriptionId (mandatory) contains the subscriptionId of a previously created HLR delivery receipt subscription
     */
    @Override
    public void removeHLRDeliveryNotificationsSubscription(String subscriptionId) {
        RequestData requestData = new RequestData(HLR_SUBSCRIPTION_URL_BASE + "/" + encodeURLParam(subscriptionId), Method.DELETE);
        executeMethod(requestData);
    }

    /**
     * Add OneAPI PUSH 'HLR' Notifications listener and start push server simulator
     *
     * @param listener
     */
    public void addPushHLRNotificationsListener(HLRNotificationsListener listener) {
        if (listener == null) {
            return;
        }

        if (hlrPushListenerList == null) {
            hlrPushListenerList = new ArrayList<HLRNotificationsListener>();
        }

        hlrPushListenerList.add(listener);

        startHLRPushServerSimulator();

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Listener is successfully added, push server is started and is waiting for HLR Notifications");
        }
    }

    /**
     * Returns HLR Notifications PUSH Listeners list
     *
     * @return List<HLRNotificationsListener>
     */
    public List<HLRNotificationsListener> getHLRPushNotificationListeners() {
        return hlrPushListenerList;
    }

    /**
     * Remove PUSH HLR listeners and stop server
     */
    public void removePushHLRNotificationsListeners() {
        stopHLRPushServerSimulator();
        hlrPushListenerList = null;

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("HLR Listeners are successfully removed.");
        }
    }

    private void startHLRPushServerSimulator() {
        if (hlrPushServerSimulator == null) {
            hlrPushServerSimulator = new PushServerSimulator(this, getConfiguration().getHlrPushServerSimulatorPort());
            hlrPushServerSimulator.start();
        }
    }

    private void stopHLRPushServerSimulator() {
        if (hlrPushServerSimulator != null) {
            hlrPushServerSimulator.stop();
        }
    }
}
