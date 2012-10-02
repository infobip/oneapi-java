package oneapi.config;

import java.io.File;

import oneapi.exception.ConfigurationException;
import oneapi.model.Authentication;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonSerialize(include = Inclusion.NON_NULL)
public class Configuration  {
    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
    private static final String DEFAULT_CONFIG_FILE = "etc/client.cfg";
    private static final String CONFIG_FILE_SYSTEM_PROPERTY = "sms.client.config";

    private Authentication authentication = new Authentication();
	private String apiUrl = "http://api.parseco.com";
	private String versionOneAPISMS = "1";
	private int inboundMessagesRetrievingInterval = 5000;
	private int dlrRetrievingInterval = 5000;
	private int dlrStatusPushServerSimulatorPort = 3000;
	private int inboundMessagesPushServerSimulatorPort = 3001;
	private int hlrPushServerSimulatorPort = 3002;
	
	/**
	 * Initialize configuration object, Login have to be done after initialization 
	 */
	public Configuration() {  
	}
	
	/**
	 * Initialize Configuration object 
	 * @param loadFromFile determines if data will be loaded from the configuration file
	 */
	public Configuration(boolean loadFromFile) {
		if(loadFromFile){		
			load();
		}
	}

	/**
	 * Initialize configuration object using the 'IBSSO' Authentication credentials
	 * @param username - 'IBSSO' Authentication user name
	 * @param password - 'IBSSO' Authentication password
	 */
	public Configuration(String username, String password) {  
		this.authentication.setUsername(username);
		this.authentication.setPassword(password);
		this.authentication.setType(Authentication.AuthType.BASIC);
	}

	/**
	 * Initialize configuration object using the 'OAuth' Authentication
	 * @param accessToken - 'OAuth' Authentication Access Token
	 */
	public Configuration(String accessToken) {  
		this.authentication.setAccessToken(accessToken);
		this.authentication.setType(Authentication.AuthType.OAUTH);
	}
	
	/**
	 * Initialize configuration object using the 'IBSSO' Authentication credentials
	 * @param messagingBaseUrl - Base URL containing host name and port of the OneAPI SMS server
	 * @param versionOneAPISMS - Version of OneAPI SMS you are accessing (the default is the latest version supported by that server)
	 * @param username - 'IBSSO' Authentication user name
	 * @param password - 'IBSSO' Authentication password
	 */
	public Configuration(String messagingBaseUrl, String versionOneAPISMS, String username, String password) {  
		this(username, password);
		this.apiUrl = messagingBaseUrl;
		this.versionOneAPISMS = versionOneAPISMS;
	}

	/**
	 * Initialize configuration object using the 'OAuth' Authentication
	 * @param messagingBaseUrl - Base URL containing host name and port of the OneAPI SMS server
	 * @param versionOneAPISMS - Version of OneAPI SMS you are accessing (the default is the latest version supported by that server)
	 * @param accessToken - 'OAuth' Authentication Access Token
	 */
	public Configuration(String messagingBaseUrl, String versionOneAPISMS, String accessToken) {  
		this(accessToken);
		this.apiUrl = messagingBaseUrl;
		this.versionOneAPISMS = versionOneAPISMS;
	}
	

	/**
	 * Load data from the configuration file 
	 */
	public void load() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_COMMENTS, true);

			String configFileName = System.getProperty(CONFIG_FILE_SYSTEM_PROPERTY, DEFAULT_CONFIG_FILE);	
			Configuration tmpConfig = mapper.readValue(new File(configFileName), Configuration.class);

			authentication = tmpConfig.authentication;
			apiUrl = tmpConfig.apiUrl;
			versionOneAPISMS = tmpConfig.versionOneAPISMS;
			inboundMessagesRetrievingInterval = tmpConfig.inboundMessagesRetrievingInterval;
			dlrRetrievingInterval = tmpConfig.dlrRetrievingInterval;
			
			if (LOGGER.isInfoEnabled()) LOGGER.info("Data successfully loaded from '{}' configuration file.", configFileName);
			
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
	
	/**
	 * Save data to the configuration file 
	 */
	public void save() {	
		try {
			String configFileName = System.getProperty("sms.client.config", "client.cfg");
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(configFileName), this);

			if (LOGGER.isInfoEnabled()) LOGGER.info("Data successfully saved to '{}' configuration file.", configFileName);
		
		} catch (Exception e) {
			throw new ConfigurationException(e);
		}
	}
	
	/**
	 * Object containing 'OneAPI' Authentication data
	 * @return Authentication
	 */
	public Authentication getAuthentication() {	
		return authentication;
	}

	/**
	 * Object containing 'OneAPI' Authentication data
	 * @param value
	 */
	public void setAuthentication(Authentication value) {
		this.authentication = value;
	}

	/**
	 * Base URL containing host name and port of the OneAPI SMS server
	 * @return messagingBaseUrl
	 */
	public String getApiUrl() {
		return apiUrl;
	}

	/**
	 * Base URL containing host name and port of the OneAPI SMS server
	 * @param apiUrl
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * Version of OneAPI SMS you are accessing (the default is the latest version supported by that server)
	 * @return versionOneAPISMS
	 */
	public String getVersionOneAPISMS() {
		return versionOneAPISMS;
	}

	/**
	 * Version of OneAPI SMS you are accessing (the default is the latest version supported by that server)
	 * @param versionOneAPISMS
	 */
	public void setVersionOneAPISMS(String versionOneAPISMS) {
		this.versionOneAPISMS = versionOneAPISMS;
	}
	
	/**
	 * Interval to automatically pool inbounds messages in milliseconds	
	 * @return inboundMessagesRetrievingInterval
	 */
	public int getInboundMessagesRetrievingInterval() {
		return inboundMessagesRetrievingInterval;
	}

	/**
	 * Interval to automatically pool inbounds messages in milliseconds	
	 * @param inboundMessagesRetrievingInterval
	 */
	public void setInboundMessagesRetrievingInterval(int inboundMessagesRetrievingInterval) {
		this.inboundMessagesRetrievingInterval = inboundMessagesRetrievingInterval;
	}

	/**
	 * Interval to automatically pool delivery reports in milliseconds	
	 * @return dlrRetrievingInterval
	 */
	public int getDlrRetrievingInterval() {
		return dlrRetrievingInterval;
	}

	/**
	 * Interval to automatically pool delivery reports in milliseconds	
	 * @param dlrRetrievingInterval
	 */
	public void setDlrRetrievingInterval(int dlrRetrievingInterval) {
		this.dlrRetrievingInterval = dlrRetrievingInterval;
	}

	/**
	 * Delivery Notification Status Push server port (default = 3000)
	 * @return dlrStatusPushServerSimulatorPort
	 */
	public int getDlrStatusPushServerSimulatorPort() {
		return dlrStatusPushServerSimulatorPort;
	}

	/**
	 * Delivery Notification Status Push server port (default = 3000)
	 * @param dlrStatusPushServerSimulatorPort
	 */
	public void setDlrStatusPushServerSimulatorPort(int dlrStatusPushServerSimulatorPort) {
		this.dlrStatusPushServerSimulatorPort = dlrStatusPushServerSimulatorPort;
	}

	/**
	 * Inbound Messages Notifications Push server port (default = 3001)
	 * @return inboundMessagesPushServerSimulatorPort
	 */
	public int getInboundMessagesPushServerSimulatorPort() {
		return inboundMessagesPushServerSimulatorPort;
	}

	/**
	 * Inbound Messages Notifications Push server port (default = 3001)
	 * @param inboundMessagesPushServerSimulatorPort
	 */
	public void setInboundMessagesPushServerSimulatorPort(
			int inboundMessagesPushServerSimulatorPort) {
		this.inboundMessagesPushServerSimulatorPort = inboundMessagesPushServerSimulatorPort;
	}

	/**
	 * HLR Notifications Push server port (default = 3002)
	 * @return hlrPushServerSimulatorPort
	 */
	public int getHlrPushServerSimulatorPort() {
		return hlrPushServerSimulatorPort;
	}

	/**
	 * HLR Notifications Push server port (default = 3002)
	 * @param hlrPushServerSimulatorPort
	 */
	public void setHlrPushServerSimulatorPort(int hlrPushServerSimulatorPort) {
		this.hlrPushServerSimulatorPort = hlrPushServerSimulatorPort;
	}
}

