package oneapi.test;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.exception.RequestException;
import oneapi.model.*;
import oneapi.model.common.DeliveryInfoList;
import oneapi.model.common.DeliveryReceiptSubscription;
import oneapi.model.common.DeliveryReportSubscription;
import oneapi.model.common.InboundSMSMessage;
import oneapi.model.common.InboundSMSMessageList;
import oneapi.model.common.ResourceReferenceWrapper;
import oneapi.model.common.DeliveryInfoList.DeliveryInfo;
import oneapi.model.common.DeliveryReceiptSubscription.CallbackReference;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;

public class TestOneAPI {

    public static final int SERVERPORT = 8081;
    public static final String URL = "http://localhost:8081";
    public static final String ONEAPI_VERSION = "1";
    public static final String USERNAME = "simple";
    public static final String PASSWORD = "simple";
    public static final String SENDER_ADDRESS = "TestSender";
    public static final String DESTINATION_ADDRESS = "TestDestination";
    public static final String RECIPIENT_ADDRESS = "1111";
    public static final String MESSAGE_TEXT = "TestMessageText";
    public static final String CLIENT_CORRELATOR = "TestClientCorrelator";
    public static final String NOTIFY_URL = "http://TestNotifyUrl";
    public static final String SENDER_NAME = "TestSenderName";
    public static final String CALLBACK_DATA = "TestCallbackData";
    public static final String REQUEST_ID = "TestRequestId";
    public static final String SUBSCRIPTION_ID = "TestSubscriptionId";
    public static final String CRITERIA = "TestCriteria";
    public static final String NOTIFICATION_FORMAT = "TestNotificationFormat";
    public static final String MESSAGE_ID = "ID1";
    private static SMSClient client = null;
    private static OneAPIServerSimulator server = null;

    @BeforeClass
    public static void startSimulator() throws Exception {
        // initialize http server
        server = new OneAPIServerSimulator(SERVERPORT);
        new Thread(server).start();

        // initialize client using OneAPIConfig
        client = new SMSClient(createOneAPIConfig());
    }

    @AfterClass
    public static void stopSimulator() {
        // kill oneAPI server
        server.release();
        server = null;
        client = null;
    }

    @Test
    public void testSendSimpleSMS() {
        server.setResponse(getResourceReferenceJson());

        ResourceReferenceWrapper resourceReferenceWrapper = getResourceReferenceWrapper();
        SendMessageResult response = null;
        SMSRequest smsRequest = new SMSRequest(SENDER_ADDRESS, MESSAGE_TEXT, RECIPIENT_ADDRESS);

        try {
            response = client.getSMSMessagingClient().sendSMS(smsRequest);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to send the message. Err: " + e.getMessage());
        }
        Assert.assertNotNull(response);
        Assert.assertEquals(resourceReferenceWrapper.getResourceReference().getResourceURL(), response.getResourceReference().getResourceURL());
        // compare posted message parameters with the reference parameters
        Assert.assertEquals("{\"senderAddress\":\"" + SENDER_ADDRESS + "\",\"address\":[\"" + RECIPIENT_ADDRESS + "\"],\"message\":\"" + MESSAGE_TEXT + "\",\"clientCorrelator\":null,\"notifyURL\":null,\"senderName\":null,\"callbackData\":null,\"language\":null}", server.getPostRequest());
    }

    @Test
    public void testSendMultipleSMS() {
        server.setResponse(getResourceReferenceJson());

        ResourceReferenceWrapper resourceReferenceWrapper = getResourceReferenceWrapper();

        SMSRequest sms = composeSms();

        SendMessageResult response = null;
        try {
            response = client.getSMSMessagingClient().sendSMS(sms);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to send the message. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(resourceReferenceWrapper.getResourceReference().getResourceURL(), response.getResourceReference().getResourceURL());
        // compare posted message parameters with the reference parameters
        Assert.assertEquals("{\"senderAddress\":\"" + SENDER_ADDRESS + "\",\"address\":[\"2222\",\"3333\",\"4444\",\"5555\"],\"message\":\"" + MESSAGE_TEXT + "\",\"clientCorrelator\":\"" + CLIENT_CORRELATOR + "\",\"notifyURL\":\"" + NOTIFY_URL + "\",\"senderName\":\"" + SENDER_NAME + "\",\"callbackData\":\"" + CALLBACK_DATA + "\",\"language\":null}", server.getPostRequest());
    }

    @Test
    public void testQueryDeliveryStatus() {
        server.setResponse(getDeliveryInfoListJson());

        DeliveryInfoList deliveryInfoList = getDeliveryInfoList();

        DeliveryInfoList response = null;
        try {
            response = client.getSMSMessagingClient().queryDeliveryStatus(SENDER_ADDRESS, REQUEST_ID);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to query delivery status. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);

        // check if delivery info exists
        Assert.assertNotNull(response.getDeliveryInfo());
        Assert.assertNotNull(response.getDeliveryInfo().get(0));

        // compare response parameters with the reference parameters
        Assert.assertEquals(deliveryInfoList.getResourceURL(), response.getResourceURL());
        Assert.assertEquals(deliveryInfoList.getDeliveryInfo().get(0).getAddress(), response.getDeliveryInfo().get(0).getAddress());
        Assert.assertEquals(deliveryInfoList.getDeliveryInfo().get(0).getDeliveryStatus(), response.getDeliveryInfo().get(0).getDeliveryStatus());
    }

    @Test
    public void subscribeToDeliveryNotificationsWithoutOptionalParams() {
        server.setResponse(getDeliveryReceiptSubscriptionJson());

        DeliveryReceiptSubscription deliveryReceiptSubscription = getDeliveryReceiptSubscription();

        String response = null;
        try {
            response = client.getSMSMessagingClient().subscribeToDeliveryStatusNotifications(new SubscribeToDeliveryNotificationsRequest(SENDER_ADDRESS, NOTIFY_URL));
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to subcribe to delivery notifications. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(GetIdFromResourceUrl(deliveryReceiptSubscription.getResourceURL()), response);
        // compare posted subscription parameters with the reference parameters
        Assert.assertEquals("{\"senderAddress\":\"" + SENDER_ADDRESS + "\",\"notifyURL\":\"" + NOTIFY_URL + "\",\"criteria\":null,\"clientCorrelator\":null,\"callbackData\":null}", server.getPostRequest());
    }

    @Test
    public void subscribeToDeliveryNotificationsWithOptionalParams() {
        server.setResponse(getDeliveryReceiptSubscriptionJson());

        DeliveryReceiptSubscription deliveryReceiptSubscription = getDeliveryReceiptSubscription();

        String response = null;
        try {
            response = client.getSMSMessagingClient().subscribeToDeliveryStatusNotifications(new SubscribeToDeliveryNotificationsRequest(SENDER_ADDRESS, NOTIFY_URL, CRITERIA, CLIENT_CORRELATOR, CALLBACK_DATA));
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to subcribe to delivery notifications. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(GetIdFromResourceUrl(deliveryReceiptSubscription.getResourceURL()), response);
        // compare posted subscription parameters with the reference parameters
        Assert.assertEquals("{\"senderAddress\":\"" + SENDER_ADDRESS + "\",\"notifyURL\":\"" + NOTIFY_URL + "\",\"criteria\":\"" + CRITERIA + "\",\"clientCorrelator\":\"" + CLIENT_CORRELATOR + "\",\"callbackData\":\"" + CALLBACK_DATA + "\"}", server.getPostRequest());
    }

    @Test
    public void cancelDeliveryNotifications() {
        try {
            client.getSMSMessagingClient().removeDeliveryNotificationsSubscription(SUBSCRIPTION_ID);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to cancel delivery notifications. Err: " + e.getMessage());
        }
    }

    @Test
    public void getInboundMessages() {
        server.setResponse(getInboundSMSMessageListJson());

        InboundSMSMessageList inboundSMSMessageList = getInboundSMSMessageList();
        InboundSMSMessageList response = null;

        try {
            response = client.getSMSMessagingClient().getInboundMessages(100);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to get inbound messages. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);

        // check if inbound message exists
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getInboundSMSMessage()[0]);

        // compare response parameters with the reference parameters
        Assert.assertEquals(inboundSMSMessageList.getResourceURL(), response.getResourceURL());
        Assert.assertEquals(inboundSMSMessageList.getNumberOfMessagesInThisBatch(), response.getNumberOfMessagesInThisBatch());
        Assert.assertEquals(inboundSMSMessageList.getTotalNumberOfPendingMessages(), response.getTotalNumberOfPendingMessages());
        Assert.assertEquals(inboundSMSMessageList.getInboundSMSMessage()[0].getSenderAddress(), response.getInboundSMSMessage()[0].getSenderAddress());
        Assert.assertEquals(inboundSMSMessageList.getInboundSMSMessage()[0].getDestinationAddress(), response.getInboundSMSMessage()[0].getDestinationAddress());
        Assert.assertEquals(inboundSMSMessageList.getInboundSMSMessage()[0].getMessage(), response.getInboundSMSMessage()[0].getMessage());
        Assert.assertEquals(inboundSMSMessageList.getInboundSMSMessage()[0].getMessageId(), response.getInboundSMSMessage()[0].getMessageId());
    }

    @Test
    public void subscribeToReceiptNotificationsWithoutOptionalParams() {
        server.setResponse(getResourceReferenceJson());

        ResourceReferenceWrapper resourceReferenceWrapper = getResourceReferenceWrapper();

        String response = null;

        try {
            response = client.getSMSMessagingClient().subscribeToInboundMessagesNotifications(new SubscribeToInboundMessagesRequest(DESTINATION_ADDRESS, NOTIFY_URL));
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to subcribe to receipt notifications. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(GetIdFromResourceUrl(resourceReferenceWrapper.getResourceReference().getResourceURL()), response);
        // compare posted subscription parameters with the reference parameters
        Assert.assertEquals("{\"destinationAddress\":\"" + DESTINATION_ADDRESS + "\",\"notifyURL\":\"" + NOTIFY_URL + "\",\"criteria\":null,\"notificationFormat\":null,\"clientCorrelator\":null,\"callbackData\":null}", server.getPostRequest());
    }

    @Test
    public void subscribeToReceiptNotificationsWithOptionalParams() {
        server.setResponse(getResourceReferenceJson());

        ResourceReferenceWrapper resourceReferenceWrapper = getResourceReferenceWrapper();

        String response = null;
        try {
            response = client.getSMSMessagingClient().subscribeToInboundMessagesNotifications(new SubscribeToInboundMessagesRequest(DESTINATION_ADDRESS, NOTIFY_URL, CRITERIA, NOTIFICATION_FORMAT, CLIENT_CORRELATOR, CALLBACK_DATA));
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to subcribe to receipt notifications. Err: " + e.getMessage());
        }

        Assert.assertNotNull(response);
        Assert.assertEquals(GetIdFromResourceUrl(resourceReferenceWrapper.getResourceReference().getResourceURL()), response);
        // compare posted subscription parameters with the reference parameters
        Assert.assertEquals("{\"destinationAddress\":\"" + DESTINATION_ADDRESS + "\",\"notifyURL\":\"" + NOTIFY_URL + "\",\"criteria\":\"" + CRITERIA + "\",\"notificationFormat\":\"" + NOTIFICATION_FORMAT + "\",\"clientCorrelator\":\"" + CLIENT_CORRELATOR + "\",\"callbackData\":\"" + CALLBACK_DATA + "\"}", server.getPostRequest());
    }

    @Test
    public void cancelReceiptNotifications() {
        int response = 0;
        try {
            client.getSMSMessagingClient().removeInboundMessagesSubscription(SUBSCRIPTION_ID);
        } catch (RequestException e) {
            Assert.fail("Error occured while trying to receipt notificationss. Err: " + e.getMessage());
        }
        Assert.assertNotNull(response);
    }

    @Test
    public void getDLRSubscriptions() {
        server.setResponse(getDlrSubscriptionsJson());

        DeliveryReportSubscription[] deliveryReportSubscriptions = getDlrSubscriptions();

        try {
            DeliveryReportSubscription[] response = client.getSMSMessagingClient().getDeliveryNotificationsSubscriptionsBySender(SENDER_ADDRESS);

            Assert.assertNotNull(response);
            Assert.assertTrue(response.length > 0);

            // compare response parameters with the reference parameters
            Assert.assertEquals(deliveryReportSubscriptions[0].getCallbackData(), response[0].getCallbackData());
            Assert.assertEquals(deliveryReportSubscriptions[0].getCriteria(), response[0].getCriteria());
            Assert.assertEquals(deliveryReportSubscriptions[0].getNotifyUrl(), response[0].getNotifyUrl());
            Assert.assertEquals(deliveryReportSubscriptions[0].getSenderAddress(), response[0].getSenderAddress());
            Assert.assertEquals(deliveryReportSubscriptions[0].getSubscriptionId(), response[0].getSubscriptionId());

        } catch (Exception e) {
            Assert.fail("Error occured while trying to get DLR subscriptions for specified mobile terminal. Err: " + e.getMessage());
        }
    }

    private static Configuration createOneAPIConfig() {
        Configuration config = new Configuration(URL, USERNAME, ONEAPI_VERSION, PASSWORD);
        return config;
    }

    private static SMSRequest composeSms() {
        String[] address = new String[4];
        address[0] = "2222";
        address[1] = "3333";
        address[2] = "4444";
        address[3] = "5555";

        SMSRequest sms = new SMSRequest(SENDER_ADDRESS, MESSAGE_TEXT, address);
        sms.setClientCorrelator(CLIENT_CORRELATOR);
        sms.setNotifyURL(NOTIFY_URL);
        sms.setSenderName(SENDER_NAME);
        sms.setCallbackData(CALLBACK_DATA);

        return sms;
    }

    public static String getDlrSubscriptionsJson() {
        return TestJsonProvider.convertToJson(TestJsonProvider.getEntityWithRoot("deliveryReceiptSubscriptions", getDlrSubscriptions()));
    }

    private static DeliveryReportSubscription[] getDlrSubscriptions() {
        DeliveryReportSubscription[] deliveryReportSubscriptions = new DeliveryReportSubscription[1];
        DeliveryReportSubscription deliveryReportSubscription = new DeliveryReportSubscription();
        deliveryReportSubscription.setSubscriptionId(REQUEST_ID);
        deliveryReportSubscription.setCallbackData(CRITERIA);
        deliveryReportSubscription.setNotifyUrl(NOTIFY_URL);
        deliveryReportSubscription.setSenderAddress(SENDER_ADDRESS);
        deliveryReportSubscriptions[0] = deliveryReportSubscription;
        return deliveryReportSubscriptions;
    }

    private static ResourceReferenceWrapper getResourceReferenceWrapper() {
        ResourceReferenceWrapper resourceReferenceWrapper = new ResourceReferenceWrapper("http://example.com/1/smsmessaging/outbound/tel%3A%2B12345678/requests/abc123");
        return resourceReferenceWrapper;
    }

    private static String getResourceReferenceJson() {
        return TestJsonProvider.convertToJson(getResourceReferenceWrapper());
    }

    private static DeliveryReceiptSubscription getDeliveryReceiptSubscription() {
        DeliveryReceiptSubscription deliveryReceiptSubscription = new DeliveryReceiptSubscription();
        deliveryReceiptSubscription.setResourceURL("http://example.com/1/smsmessaging/outbound/tel%3A%2B12345678/requests/abc123");
        deliveryReceiptSubscription.setCallbackReference(new CallbackReference());
        return deliveryReceiptSubscription;
    }

    private static String getDeliveryReceiptSubscriptionJson() {
        return TestJsonProvider.convertToJson(TestJsonProvider.getEntityWithRoot("deliveryReceiptSubscription", getDeliveryReceiptSubscription()));
    }

    private static DeliveryInfoList getDeliveryInfoList() {
        DeliveryInfoList deliveryInfoList = new DeliveryInfoList();
        deliveryInfoList.setResourceURL("http://example.com/1/smsmessaging/outbound/38595111111111111/requests/abc123");

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setDeliveryStatus("DELIVERED");
        deliveryInfo.setAddress("38595111111111111");

        List<DeliveryInfo> newList = new ArrayList<DeliveryInfo>();
        newList.add(deliveryInfo);

        deliveryInfoList.setDeliveryInfo(newList);

        return deliveryInfoList;
    }

    private static String getDeliveryInfoListJson() {
        return TestJsonProvider.convertToJson(TestJsonProvider.getEntityWithRoot("deliveryInfoList", getDeliveryInfoList()));
    }

    private static InboundSMSMessageList getInboundSMSMessageList() {

        InboundSMSMessageList inboundSMSMessageList = new InboundSMSMessageList();

        InboundSMSMessage[] inboundSMSMessages = new InboundSMSMessage[1];

        InboundSMSMessage inboundSMSMessage = new InboundSMSMessage();
        inboundSMSMessage.setMessage(MESSAGE_TEXT);
        inboundSMSMessage.setMessageId(MESSAGE_ID);
        inboundSMSMessage.setResourceURL("");
        inboundSMSMessage.setSenderAddress(SENDER_ADDRESS);

        inboundSMSMessages[0] = inboundSMSMessage;

        inboundSMSMessageList.setInboundSMSMessage(inboundSMSMessages);

        inboundSMSMessageList.setNumberOfMessagesInThisBatch(1);
        inboundSMSMessageList.setTotalNumberOfPendingMessages(0);
        inboundSMSMessageList.setResourceURL("http://example.com/1/smsmessaging/outbound/38595111111111111/requests/abc123");

        return inboundSMSMessageList;
    }

    private static String getInboundSMSMessageListJson() {
        return TestJsonProvider.convertToJson(TestJsonProvider.getEntityWithRoot("inboundSMSMessageList", getInboundSMSMessageList()));
    }

    protected String GetIdFromResourceUrl(String resourceUrl) {
        String id = "";
        if (resourceUrl.contains("/")) {
            String[] arrResourceUrl = resourceUrl.split("/");
            if (arrResourceUrl.length > 0) {
                id = arrResourceUrl[arrResourceUrl.length - 1];
            }
        }

        return id;
    }
}
