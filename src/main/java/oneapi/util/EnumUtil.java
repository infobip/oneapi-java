package oneapi.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Contains helper methods for transforming integer values to to specific enumeration constants.<br>
 *   
 *
 */
public final class EnumUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Enumeration which are to be handled using EnumUtil methods must implement this interface.
	 * 
	 * @author mzagar
	 *
	 */
	public interface IEnumIntValue {
		int getValue();
	}
	
	/**
	 * Returns integer value for specified enumeration element.<br>
	 * <br>
	 * This method is implement for convenience only - same value can be acquired directly from enumeration constant
	 * using {@link IEnumIntValue#getValue()}.
	 *  
	 * @param <T> enumeration class which must implement {@link IEnumIntValue}
	 * @param enumValue enumeration constant for which to get associated integer value
	 * @return integer value associated with enumeration constant
	 */
	public static <T extends IEnumIntValue> int valueFor(T enumValue) {
		return enumValue.getValue();
	}

	/**
	 * Returns enumeration constant which is associated with specified integer value.<br>
	 * <br>
	 * Complexity of this method is O(n). 
	 *   
	 * @param <T> Type of enumeration
	 * @param enumClass enumeration class
	 * @param i value associated to enumeration constant
	 * @return first enumeration constant found which is associated with specified integer value
	 * @throws IllegalArgumentException if specified class is not enumeration class
	 */
	public static <T extends IEnumIntValue> T enumFor(Class<T> enumClass, int i) throws IllegalArgumentException {
	
		T[] enums = getEnumConstants(enumClass);
	
		for (T en : enums) {
			if (en.getValue() == i) {
				return en;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns enumerations constants defined in specified enumeration class.<br>
	 * 
	 * @param <T> type of enumeration
	 * @param enumClass enumeration class
	 * @return enumeration constants
	 * @throws IllegalArgumentException if specified class is not enumeration class
	 */
	protected static <T extends IEnumIntValue> T[] getEnumConstants(Class<T> enumClass) throws IllegalArgumentException {
		T[] enums = enumClass.getEnumConstants();
		
		if ( enums == null ) {
			throw new IllegalArgumentException("enum class must be specified");
		}
		
		return enums;
	}
	
	/**
	 * Returns fast enumeration constant mapper object associated with specific enumeration.<br>
	 * <br>
	 * If enumeration contains lots of enumeration constants, {@link #enumFor(Class, int)} can become a performance issue.<br>
	 * To get O(1) performace {@link EnumMapper} should be used which can return enumeration constant for specified value in constant time.
	 * <br>
	 *      
	 */
	public static <T extends IEnumIntValue> EnumMapper<T> enumMapper(Class<T> enumClass) {
		return new EnumMapper<T>(enumClass);
	}
	
	/**
	 * Helper class for getting enumeration constants based on integer value.<br>
	 * <br>
	 * Uses map for mapping enumeration constants to specified integer value which results in O(1) complexity.<br>
	 * 
	 * @author mzagar
	 *
	 * @param <T> enumeration type - must implement {@link IEnumIntValue}
	 */
	public static class EnumMapper<T extends IEnumIntValue> {
		private Map<Integer, T> valueToEnumMap = new HashMap<Integer, T>();
		
		/**
		 * Fills int-to-enum map with values.<br>
		 * <br>
		 * If multiple enumeration constants have identical integer values associated, enumeration constant with
		 * lower ordinal number is stored in the map - latter enumeration constants are ignored.
		 *  
		 * @param enumClass enumeration class
		 */
		private EnumMapper(Class<T> enumClass) {
			for( T en : getEnumConstants(enumClass) ) {
				
				int enumValue = en.getValue();
				
				T enumInMap = valueToEnumMap.get(enumValue);
							
				if ( enumInMap == null ) {
					valueToEnumMap.put(enumValue, en);
				}
			}
		}
		
		public List<T> getList() {
			return new LinkedList<T>(valueToEnumMap.values());
		}
		
		/**
		 * Looks up enumeration constant for specified value.
		 *  
		 * @param value for which to return enumeration constant
		 * @return <li>enum constant : which has specified value associated - if multiple constants have same value, 
		 *                             constant with lower ordinal is returned</li> 
		 * 		   <li>null : if no int value is associated with specified enumeration constant</li>
		 */
		public T enumFor(int value) {
			return valueToEnumMap.get(value);
		}
		
		/**
		 * Convenience method - returns int value associated with specified enumeration constant.<br>
		 * <br>
		 * Returns same value as {@link IEnumIntValue#getValue()}.
		 *  
		 * @param enumValue for which to return associated integer value
		 * @return integer value associated with specified enumeration constant
		 */
		public int valueFor(T enumValue) {
			return enumValue.getValue();
		}
	}

}
