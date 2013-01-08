/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oneapi.examples.quickstart;

import java.util.List;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.SMSRequest;
import oneapi.model.SendMessageResult;
import oneapi.model.common.DeliveryInfoList;
import oneapi.model.common.DeliveryInfoList.DeliveryInfo;

/**
 *
 * @author isipka
 */
public class HelloWorld {

    public static String USERNAME = ExamplesAccountData.USERNAME;
    public static String PASSWORD = ExamplesAccountData.PASSWORD;
    public static String SENDER = ExamplesAccountData.SENDER;
    public static String MESSAGE = ExamplesAccountData.MESSAGE;
    public static String DESTINATION = ExamplesAccountData.DESTINATION;

    public static void main(String[] args) {

        Configuration configuration = new Configuration(USERNAME, PASSWORD);
        SMSClient smsClient = new SMSClient(configuration);

        SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);

        SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);

        // The client correlator is a unique identifier of this api call:
        String clientCorrelator = sendMessageResult.getClientCorrelator();

        DeliveryInfoList deliveryInfoList = smsClient.getSMSMessagingClient().queryDeliveryStatus(SENDER, clientCorrelator);
        List<DeliveryInfo> deliveryInfos = deliveryInfoList.getDeliveryInfo();
        
        for (DeliveryInfo deliveryInfo : deliveryInfos) {
            System.out.println("message for "+deliveryInfo.getAddress()+" has delivery status "+deliveryInfo.getDeliveryStatus());
        }


    }
}
