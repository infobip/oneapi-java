package oneapi.test;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJsonProvider {

	private static ObjectMapper jsonMapper = new ObjectMapper();
	

	/**
	 * Basically a try/catch block for jackson's ObjectMapper jsonMapper
	 * @param o
	 * @return
	 */
	public static String convertToJson(Object o) {
		try {
			return jsonMapper.writeValueAsString(o);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Puts the object into a map under the key rootJsonTag and returns that map.
	 * Suitable for placing a list of objects into one root JSON object.
	 * @param rootJsonTag
	 * @param objectToWrap
	 * @return
	 */
	public static Map<String, Object> getEntityWithRoot(String rootJsonTag, Object objectToWrap) {
		Map<String, Object> wrapper = new HashMap<String, Object>(2);
		wrapper.put(rootJsonTag, objectToWrap);
		return wrapper;
	}
	
}
