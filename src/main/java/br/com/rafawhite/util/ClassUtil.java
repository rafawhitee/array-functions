package br.com.rafawhite.util;

import java.io.Serializable;
import java.lang.reflect.Method;

public abstract class ClassUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET = "get";
	public static final String SET = "set";
	public static final String IS = "is";

	public static String verifyIfExistsGet(String name, Object genericObject) {
		// First check with get
		String nameWithGet = verifyIfExistsGetOrSet(name, GET);
		Method methodWithGet = getMethodByName(nameWithGet, genericObject);
		if(methodWithGet != null)
			return nameWithGet;
		
		// If methodWithGet is null, check with IS (boolean fields)
		String nameWithIs = verifyIfExistsGetOrSet(name, IS);
		Method methodWithIs = getMethodByName(nameWithIs, genericObject);
		if(methodWithIs != null)
			return nameWithIs;
		
		return null;
	}

	public static String verifyIfExistsSet(String name, Object genericObject) {
		String nameWithSet = verifyIfExistsGetOrSet(name, SET);
		Method setter = getMethodByName(nameWithSet, genericObject);
		if(setter != null)
			return nameWithSet;
		
		return null;
	}
	
	public static String verifyIfExistsGet(String name, Class<?> objectClass) {
		// First check with get
		String nameWithGet = verifyIfExistsGetOrSet(name, GET);
		Method methodWithGet = getMethodByName(nameWithGet, objectClass);
		if(methodWithGet != null)
			return nameWithGet;
		
		// If methodWithGet is null, check with IS (boolean fields)
		String nameWithIs = verifyIfExistsGetOrSet(name, IS);
		Method methodWithIs = getMethodByName(nameWithIs, objectClass);
		if(methodWithIs != null)
			return nameWithIs;
		
		return null;
	}

	public static String verifyIfExistsSet(String name, Class<?> objectClass) {
		String nameWithSet = verifyIfExistsGetOrSet(name, SET);
		Method setter = getMethodByName(nameWithSet, objectClass);
		if(setter != null)
			return nameWithSet;
		
		return null;
	}

	public static Object invokeMethod(String name, Object genericObject, Object... parametersToInvoke) throws Exception {
		Method method = getMethodByName(name, genericObject);
		if (method != null) {
			if (parametersToInvoke != null && parametersToInvoke.length > 0) {
				return method.invoke(genericObject, parametersToInvoke);
			}

			return method.invoke(genericObject);
		}
		return null;
	}

	public static Method getMethodByName(String name, Object genericObject) {
		if (genericObject != null)
			return getMethodByName(name, genericObject.getClass());

		return null;
	}

	public static Method getMethodByName(String name, Class<?> objectClass) {
		try {
			Method method = objectClass.getMethod(name);
			return method;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String verifyIfExistsGetOrSet(String name, String type) {
		if (name != null) {
			if (name.contains(type))
				return name;

			String newName = putFirstLetterUpperCase(name);
			return type + newName;
		}
		return null;
	}
	
	public static String putFirstLetterUpperCase(String word) {
		if(word != null && word.trim() != null && word.trim().length() > 0) {
			String first = word.substring(0, 1);
			return first.toUpperCase() + word.substring(1);
		}
		return null;
	}

}