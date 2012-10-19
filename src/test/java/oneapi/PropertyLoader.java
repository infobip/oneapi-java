package oneapi;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This is just for tests.
 *
 * @author Tomo Krajina
 */
public class PropertyLoader {

	public static String loadProperty(String propertyFile, String propertyName) {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(propertyFile));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties.getProperty(propertyName);
	}

}