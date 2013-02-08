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

    SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);


Later you can query for the delivery status of the message:

    DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus(SENDER, clientCorrelator);
          List<DeliveryInfoList.DeliveryInfo> deliveryInfos = deliveryInfoList.getDeliveryInfo();
          for (DeliveryInfoList.DeliveryInfo deliveryInfo : deliveryInfos) {
              System.out.println("message for "+deliveryInfo.getAddress()+" has delivery status "+deliveryInfo.getDeliveryStatus());
          }


Possible statuses are: **DeliveredToTerminal**, **DeliveryUncertain**, **DeliveryImpossible**, **MessageWaiting** and **DeliveredToNetwork**.

Messaging with notification push example
-----------------------

Same as with the standard messaging example, but when preparing your message:

    SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);
    smsRequest.setNotifyURL(NOTIFY_URL);


When the delivery notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    DeliveryInfoNotification deliveryInfoNotification = smsClient.getSMSMessagingClient().convertJsonToDeliveryInfoNotification(JSON);


Number context example
-----------------------

Initialize and login the data connection client:

    Configuration configuration = new Configuration(USERNAME, PASSWORD);
    SMSClient smsClient = new SMSClient(configuration);


Retrieve the roaming status (Number context):

    Roaming roaming = smsClient.getHLRClient().queryHLR(DESTINATION);
          System.out.println("HLR result:");
          System.out.println("servingMccMnc: "+roaming.getServingMccMnc()); 
          System.out.println("address: "+roaming.getAddress()); 
          System.out.println("currentRoaming: "+roaming.getCurrentRoaming()); 
          System.out.println("resourceURL: "+roaming.getResourceURL()); 
          System.out.println("retrievalStatus: "+roaming.getRetrievalStatus()); 
          System.out.println("callbackData: "+roaming.getCallbackData()); 
          System.out.println("extendedData: "+roaming.getExtendedData()); 
          System.out.println("IMSI: "+roaming.getExtendedData().getImsi()); 
          System.out.println("destinationAddres: "+roaming.getExtendedData().getDestinationAddress()); 
          System.out.println("originalNetworkPrefix: "+roaming.getExtendedData().getOriginalNetworkPrefix()); 
          System.out.println("portedNetworkPrefix: "+roaming.getExtendedData().getPortedNetworkPrefix());


Number context with notification push example
-----------------------

Similar to the previous example, but this time you must set the notification url where the result will be pushed:

    smsClient.getHLRClient().queryHLR(DESTINATION, NOTIFY_URL);


When the roaming status notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    RoamingNotification roamingNotification = smsClient.getHLRClient().convertJsonToHLRNotificationExample(JSON);


Retrieve inbound messages example
-----------------------

With the existing sms client (see the basic messaging example to see how to start it):

    InboundSMSMessageList inboundSMSMessageList =  smsClient.getSMSMessagingClient().getInboundMessages();
          InboundSMSMessage[] inboundSMSMessages = inboundSMSMessageList.getInboundSMSMessage();
          for (InboundSMSMessage inboundSMSMessage : inboundSMSMessages) {
              System.out.println(inboundSMSMessage.getDateTime());
              System.out.println(inboundSMSMessage.getDestinationAddress());
              System.out.println(inboundSMSMessage.getMessageId());
              System.out.println(inboundSMSMessage.getMessage());
              System.out.println(inboundSMSMessage.getResourceURL());
              System.out.println(inboundSMSMessage.getSenderAddress());
          }


Inbound message push example
-----------------------

The subscription to recive inbound messages can be set up on our site.
When the inbound message notification is pushed to your server as a HTTP POST request, you must process the body of the message with the following code:

    InboundSMSMessageList inboundSMSMessageList = smsClient.getSMSMessagingClient().convertJsonToInboundSMSMessageNotificationExample(JSON);


License
-------

This library is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
