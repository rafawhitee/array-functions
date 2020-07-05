package br.com.rafawhite.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ArrayFunctions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static List<Result> groupBy(List<?> mainList, String... fields) {
		try {
			List<Result> list = new ArrayList<Result>();
			Map<List<?>, ?> map = groupByFields(mainList, fields);
			if (map != null && map.size() > 0) {
				Set<List<?>> keys = map.keySet();
				if (keys != null && keys.size() > 0) {
					keys.forEach(currentKey -> {
						List<?> valuesOfCurrentKey = (List<?>) map.get(currentKey);
						String description = createDescriptionToResult(currentKey, Result.INITIAL_DESCRIPTION_GROUPED_BY, fields);
						list.add(new Result(description, valuesOfCurrentKey));
					});
				}
			}
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	private static Map<List<?>, ?> groupByFields(List<?> mainList, String... fields) throws Exception {
		Map<List<?>, ?> grouped = null;

		// Validate the mainList and Fields
		boolean isMainListValid = isMainListValid(mainList);
		boolean isFieldsValid = isFieldsValid(fields);

		if (isMainListValid && isFieldsValid) {

			Class<?> mainClass = mainList.get(0).getClass();

			// Parse Fields to ArrayList
			List<String> listFields = Arrays.asList(fields);

			// Create a List of Methods
			List<Method> methods = populateListMethods(mainClass, listFields);

			// Pass a MainList to Stream
			Stream<?> stream = mainList.stream();

			// Call createFunctionWithAllMethodsInvoked to create a Function with all
			// methods invoked
			Function<Object, List<Object>> compositeKey = createFunctionWithAllMethodsInvoked(methods);

			// Use the collect of Stream to groupingBy compositeKey and the returns a List
			grouped = stream.collect(Collectors.groupingBy(compositeKey, Collectors.toList()));

		}

		// Returns the Map
		return grouped;
	}

	private static String createDescriptionToResult(List<?> keys, String initialDescription, String... fields) {
		String str = initialDescription;
		if (keys != null && fields != null) {
			int keysSize = keys.size();
			int fieldsSize = fields.length;
			if (keysSize == fieldsSize && keysSize > 0) {
				for (int i = 0; i < keys.size(); i++) {
					String field = fields[i];
					Object value = keys.get(i);
					str = str + field + ": " + value;
					if (!((i + 1) == keysSize)) {
						str += ", ";
					}
				}
			}
		}
		return str;
	}

	// Create a FunctionalInterface with all Methods Invoked by each element in
	// mainList
	private static Function<Object, List<Object>> createFunctionWithAllMethodsInvoked(List<Method> methods) {
		Function<Object, List<Object>> compositeKey = obj -> {
			List<Object> methodsInvokeds = new ArrayList<Object>();
			for (Method currentMethod : methods) {
				try {
					methodsInvokeds.add(currentMethod.invoke(obj));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return methodsInvokeds;
		};
		return compositeKey;
	}

	// Returns a List<Method> that exists in mainClass
	private static List<Method> populateListMethods(Class<?> mainClass, List<String> listFields) {
		List<Method> methods = new ArrayList<Method>();
		for (String field : listFields) {
			try {
				Method newMethod = mainClass.getMethod(verifyIfExistsGet(field));
				if (newMethod != null && !methods.contains(newMethod)) {
					methods.add(newMethod);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return methods;
	}

	// Validate the mainList
	private static boolean isMainListValid(List<?> mainList) throws Exception {
		if (mainList == null || (mainList != null && mainList.isEmpty()))
			throw new Exception("Main list is null or empty");

		return true;
	}

	// Validate the Array fields
	private static boolean isFieldsValid(String... fields) throws Exception {
		if (fields == null || (fields != null && fields.length == 0))
			throw new Exception("None field to group");

		return true;
	}

	public static String verifyIfExistsGet(String name) {
		return verifyIfExistsGetOrSet(name, "get");
	}

	public static String verifyIfExistsSet(String name) {
		return verifyIfExistsGetOrSet(name, "set");
	}

	private static String verifyIfExistsGetOrSet(String name, String type) {
		if (name != null) {
			if (name.contains(type))
				return name;

			String intermedio = name.substring(0, 1);
			return type + intermedio.toUpperCase() + name.substring(1);
		}
		return null;
	}

}