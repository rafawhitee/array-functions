package br.com.rafawhite.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ClassUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET = "get";
	public static final String SET = "set";
	public static final String IS = "is";

	public static String verifyIfExistsGet(String name, Object genericObject) {
		// First check with get
		String nameWithGet = verifyIfExistsGetOrSet(name, GET);
		Method methodWithGet = getMethodByName(nameWithGet, genericObject);
		if (methodWithGet != null)
			return nameWithGet;

		// If methodWithGet is null, check with IS (boolean fields)
		String nameWithIs = verifyIfExistsGetOrSet(name, IS);
		Method methodWithIs = getMethodByName(nameWithIs, genericObject);
		if (methodWithIs != null)
			return nameWithIs;

		return null;
	}

	public static String verifyIfExistsSet(String name, Object genericObject) {
		String nameWithSet = verifyIfExistsGetOrSet(name, SET);
		Method setter = getMethodByName(nameWithSet, genericObject);
		if (setter != null)
			return nameWithSet;

		return null;
	}

	public static String verifyIfExistsGet(String name, Class<?> objectClass) {
		// First check with get
		String nameWithGet = verifyIfExistsGetOrSet(name, GET);
		Method methodWithGet = getMethodByName(nameWithGet, objectClass);
		if (methodWithGet != null)
			return nameWithGet;

		// If methodWithGet is null, check with IS (boolean fields)
		String nameWithIs = verifyIfExistsGetOrSet(name, IS);
		Method methodWithIs = getMethodByName(nameWithIs, objectClass);
		if (methodWithIs != null)
			return nameWithIs;

		return null;
	}

	public static String verifyIfExistsSet(String name, Class<?> objectClass) {
		String nameWithSet = verifyIfExistsGetOrSet(name, SET);
		Method setter = getMethodByName(nameWithSet, objectClass);
		if (setter != null)
			return nameWithSet;

		return null;
	}

	public static Object invokeMethod(String name, Object genericObject, Object... parametersToInvoke)
			throws Exception {
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
		} catch (Exception e) {
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
		if (word != null && word.trim() != null && word.trim().length() > 0) {
			String first = word.substring(0, 1);
			return first.toUpperCase() + word.substring(1);
		}
		return null;
	}

	// If name method contains . is because are a Chain of Method
	public static List<Method> getChainGetters(String name, Class<?> objectClass) throws Exception {
		
		// Check if is a CHAIN
		boolean isChainMethod = isChainMethod(name);
		
		// If true
		if(isChainMethod) {
			// Create a List<Method> to return
			List<Method> methods = new ArrayList<Method>();
			
			// Split the name with POINT
			String[] splitByPoint = name.split("\\.");
			if(splitByPoint != null && splitByPoint.length > 0) {
				// Pass a Array to ArrayList Java Class
				List<String> methodsNameSeparated = Arrays.asList(splitByPoint);
				
				// Start lastClassUsed with objectClass Paramter
				Class<?> lastClass = objectClass; 
				
				// Do a while in all names methods on List<String> methodsNameSeparated
				for(String currentMethodName : methodsNameSeparated) {
					// Each Name Method, do the Verify if exists Get, passing lastClass Used and currentName of the Iterator
					currentMethodName = verifyIfExistsGet(currentMethodName, lastClass);
					
					// Get Method and check if != null to get next class and add in the List return
					Method currentMethod = lastClass.getMethod(currentMethodName);
					if(currentMethod != null) {
						lastClass = currentMethod.getReturnType();
						methods.add(currentMethod);
					}
				}
			}
			return methods;
		} 
		// If not a Chain, create a List with a unique method
		else {
			name = verifyIfExistsGet(name, objectClass);
			Method uniqueMethod = objectClass.getMethod(name);
			return Arrays.asList(uniqueMethod);
		}
	}
	
	// If is a Chain return the first method
	public static Method getFirstMethod(String name, Class<?> objectClass) throws Exception {
		boolean isChainMethod = isChainMethod(name);
		if(isChainMethod) {
			List<Method> allMethods = getChainGetters(name, objectClass);
			if(allMethods != null && allMethods.size() > 0)
				return allMethods.get(0);
		}
		return objectClass.getMethod(name);
	}

	// Verify if is Chain Method
	public static boolean isChainMethod(String name) {
		if (name != null) {
			if (name.contains("."))
				return true;

		}
		return false;
	}

}