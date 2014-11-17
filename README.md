OneApi Java client
============================

Basic messaging example
-----------------------

First initialize the messaging client using your username and password:

    Configuration configuration = new Configuration(USERNAME, PASSWORD);
    SMSClient smsClient = new SMSClient(configuration);


An exception will be thrown if your username and/or password are incorrect.

Prepare the message:

    SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);


Send the message:

    // Store request id because we can later query for the delivery status with it:
    SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);


Later you can query for the delivery status of the message:

    DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus(SENDER, sendMessageResult.getClientCorrelator());
    String deliveryStatus = deliveryInfoList.getDeliveryInfo().get(0).getDeliveryStatus();


Possible statuses are: **DeliveredToTerminal**, **DeliveryUncertain**, **DeliveryImpossible**, **MessageWaiting** and **DeliveredToNetwork**.

Messaging with notification push example
-----------------------

Same as with the standard messaging example, but when preparing your message:

    SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);
    // The url where the delivery notification will MESSAGE pushed:
    smsRequest.setNotifyURL(NOTIFY_URL);


When the delivery notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    DeliveryInfoNotification deliveryInfoNotification = smsClient.getSMSMessagingClient().convertJsonToDeliveryInfoNotification(JSON);

Sending message with language specific characters example
-----------------------------------------------

If you want to send message with special characters, this is how you prepare your message:

	SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);
    // Create Language object with specific language code, use single shift table and use locking shift table for specific language as parameters
    Language language = new Language(LanguageCode.TR, true, false);
    // Set language
    smsRequest.setLanguage(language);

Currently supported languages (with their language codes) are: `Spanish - "SP"`, `Portuguese - "PT"`, `Turkish - "TR"`.


Number Context example
-----------------------

Initialize and login the data connection client:

    Configuration configuration = new Configuration(USERNAME, PASSWORD);
    SMSClient smsClient = new SMSClient(configuration);


Retrieve the roaming status (Number Context):

    Roaming roaming = smsClient.getHLRClient().queryHLR(DESTINATION);


Number Context with notification push example
-----------------------

Similar to the previous example, but this time you must set the notification url where the result will be pushed:

    smsClient.getHLRClient().queryHLR(DESTINATION, NOTIFY_URL);


When the roaming status notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    RoamingNotification roamingNotification = smsClient.getHLRClient().convertJsonToHLRNotificationExample(JSON);


Retrieve inbound messages example
-----------------------

With the existing sms client (see the basic messaging example to see how to start it):

    InboundSMSMessageList inboundSMSMessageList =  smsClient.getSMSMessagingClient().getInboundMessages();


Inbound message push example
-----------------------

The subscription to recive inbound messages can be set up on our site.
When the inbound message notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    InboundSMSMessageList inboundSMSMessageList = smsClient.getSMSMessagingClient().convertJsonToInboundSMSMessageNotificationExample(JSON);


License
-------

This library is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
