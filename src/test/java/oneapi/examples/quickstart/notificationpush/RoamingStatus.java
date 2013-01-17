/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oneapi.examples.quickstart.notificationpush;

import oneapi.examples.quickstart.*;
import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.Roaming;

/**
 *
 * @author isipka
 */
public class RoamingStatus {

    public static String USERNAME = ExamplesAccountData.USERNAME;
    public static String PASSWORD = ExamplesAccountData.PASSWORD;
    public static String DESTINATION = ExamplesAccountData.DESTINATION;
    public static String NOTIFY_URL = "http://192.168.10.160:9005/oneapi-java-server/roaming";

    public static void main(String[] args) {

        Configuration configuration = new Configuration(USERNAME, PASSWORD);
        SMSClient smsClient = new SMSClient(configuration);
        smsClient.getHLRClient().queryHLR(DESTINATION, NOTIFY_URL);

    }
}