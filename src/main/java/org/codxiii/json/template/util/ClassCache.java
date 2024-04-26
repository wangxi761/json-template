package org.codxiii.json.template.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiFunction;

public class ClassCache {
	private static final WeakHashMap<Class<?>, ClassCache> classCaches = new WeakHashMap<>();
	
	public static Object getProperty(Object obj, String property) {
		Class<?> clz = obj.getClass();
		ClassCache classCache = classCaches.computeIfAbsent(clz, k -> new ClassCache(clz));
		return classCache.evalProperty(obj, property);
	}
	
	private final Class<?> clz;
	private final Map<String, BiFunction<Object, String, Object>> getters;
	
	public ClassCache(Class<?> clz) {
		this.clz = clz;
		getters = new HashMap<>();
	}
	
	public Object evalProperty(Object obj, String property) {
		BiFunction<Object, String, Object> getter = getters.computeIfAbsent(property, k -> createGetter(clz, property));
		if (getter == null) {
			throw new IllegalArgumentException(String.format("No accessible field/method %s", property));
		}
		return getter.apply(obj, property);
	}
	
	@SneakyThrows
	public BiFunction<Object, String, Object> createGetter(Class<?> clz, String property) {
		if (clz == null) {
			return null;
		}
		Method method;
		try {
			method = clz.getMethod("get" + property.substring(0, 1).toUpperCase() + property.substring(1));
		} catch (NoSuchMethodException e) {
			method = null;
		}
		
		if (method != null) {
			Method finalMethod = method;
			method.setAccessible(true);
			return (o, s) -> invokeMethod(finalMethod, o);
		}
		
		Field field;
		try {
			field = clz.getDeclaredField(property);
		} catch (NoSuchFieldException e) {
			field = null;
		}
		
		if (field != null) {
			Field finalField = field;
			field.setAccessible(true);
			return (o, s) -> invokeField(finalField, o);
		}
		return createGetter(clz.getSuperclass(), property);
	}
	
	@SneakyThrows
	public Object invokeMethod(Method method, Object obj, Object... args) {
		return method.invoke(obj, args);
	}
	
	@SneakyThrows
	public Object invokeField(Field field, Object obj) {
		return field.get(obj);
	}
}
