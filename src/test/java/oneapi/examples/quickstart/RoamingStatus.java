/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oneapi.examples.quickstart;

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

public static void main(String[] args) {

Configuration configuration = new Configuration(USERNAME, PASSWORD);
SMSClient smsClient = new SMSClient(configuration);

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

}
}