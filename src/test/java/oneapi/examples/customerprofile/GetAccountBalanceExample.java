package oneapi.examples.customerprofile;

import oneapi.client.impl.SMSClient;
import oneapi.config.Configuration;
import oneapi.model.common.AccountBalance;

public class GetAccountBalanceExample {

    public static void main(String[] args) throws Exception {
    	Configuration configuration = new Configuration("user1", "user_password1");
        SMSClient smsClient = new SMSClient(configuration);

        AccountBalance accountBalance = smsClient.getCustomerProfileClient().getAccountBalance();
        System.out.println(accountBalance);
    }
}
