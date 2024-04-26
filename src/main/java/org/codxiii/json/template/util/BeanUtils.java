package org.codxiii.json.template.util;

import java.util.Map;

public class BeanUtils {
	
	public static Object getProperty(Object obj, String property) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof Map) {
			return ((Map) obj).get(property);
		}
		return ClassCache.getProperty(obj, property);
	}
	
}
