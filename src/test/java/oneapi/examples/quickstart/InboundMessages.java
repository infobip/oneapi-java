/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oneapi.examples.quickstart;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.InboundSMSMessage;
import oneapi.model.common.InboundSMSMessageList;

/**
 *
 * @author isipka
 */
public class InboundMessages {

    public static String USERNAME = ExamplesAccountData.USERNAME;
    public static String PASSWORD = ExamplesAccountData.PASSWORD;

    public static void main(String[] args) {

        Configuration configuration = new Configuration(USERNAME, PASSWORD);
        SMSClient smsClient = new SMSClient(configuration);

        InboundSMSMessageList inboundSMSMessageList = smsClient.getSMSMessagingClient().getInboundMessages();
        InboundSMSMessage[] inboundSMSMessages = inboundSMSMessageList.getInboundSMSMessage();
        for (InboundSMSMessage inboundSMSMessage : inboundSMSMessages) {
            System.out.println(inboundSMSMessage.getDateTime());
            System.out.println(inboundSMSMessage.getDestinationAddress());
            System.out.println(inboundSMSMessage.getMessageId());
            System.out.println(inboundSMSMessage.getMessage());
            System.out.println(inboundSMSMessage.getResourceURL());
            System.out.println(inboundSMSMessage.getSenderAddress());
        }

    }
}