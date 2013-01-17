/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oneapi.examples.quickstart.notificationpush;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.examples.quickstart.ExamplesAccountData;
import oneapi.model.SMSRequest;

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
    public static String NOTIFY_URL = "http://192.168.10.160:9005/oneapi-java-server/helloworld";

    public static void main(String[] args) {

        Configuration configuration = new Configuration(USERNAME, PASSWORD);
        SMSClient smsClient = new SMSClient(configuration);

        SMSRequest smsRequest = new SMSRequest(SENDER, MESSAGE, DESTINATION);
        smsRequest.setNotifyURL(NOTIFY_URL);

        smsClient.getSMSMessagingClient().sendSMS(smsRequest);       

    }
    
}
