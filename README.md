OneApi java client
============================

Basic messaging example
-----------------------

First initialize the messaging client using your username and password:

    Configuration configuration = new Configuration(username, password);
    SMSClient smsClient = new SMSClient(configuration);


Then login with the client:

    LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();


An exception will be thrown if your username and/or password are incorrect.

Prepare the message:

    SMSRequest smsRequest = new SMSRequest(senderAddress, message, recipientAddress);


Send the message:

    // Store request id because we can later query for the delivery status with it:
    String requestId = smsClient.getSMSMessagingClient().sendSMS(smsRequest);


Later you can query for the delivery status of the message:

    DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus(senderAddress, requestId);
    String deliveryStatus = deliveryInfoList.getDeliveryInfo().get(0).getDeliveryStatus();


Possible statuses are: **DeliveredToTerminal**, **DeliveryUncertain**, **DeliveryImpossible**, **MessageWaiting** and **DeliveredToNetwork**.

Messaging with notification push example
-----------------------

Same as with the standard messaging example, but when preparing your message:

    SMSRequest smsRequest = new SMSRequest(senderAddress, message, recipientAddress);
    // The url where the delivery notification will be pushed:
    smsRequest.setNotifyURL(notifyUrl);


When the delivery notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    DeliveryInfoNotification deliveryInfoNotification = smsClient.getSMSMessagingClient().convertJsonToDeliveryInfoNotification(JSON);


HLR example
-----------------------

Initialize and login the data connection client:

    Configuration configuration = new Configuration(username, password);
    SMSClient smsClient = new SMSClient(configuration);
    LoginResponse loginResponse = smsClient.getCustomerProfileClient().login();


Retrieve the roaming status (HLR):

    Roaming roaming = smsClient.getHLRClient().queryHLR(address);


HLR with notification push example
-----------------------

Similar to the previous example, but this time you must set the notification url where the result will be pushed:

    smsClient.getHLRClient().queryHLR(address, notifyUrl);


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
