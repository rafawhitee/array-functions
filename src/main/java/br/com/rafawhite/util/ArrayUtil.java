package br.com.rafawhite.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ArrayUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String AND = "AND";
	public static final String OR = "AND";
	public static final String COMMA_SEPARATOR = ",";
    
	public static <T> List<T> filterBy(List<T> mainList, List<FilterBy> filters) {
		try {
			return filterByFields(mainList, filters);
		} catch (Exception e) {
			return null;
		}
	}
	
	// Overloading of filterBy
	public static <T> List<T> filterBy(List<T> mainList, FilterBy... filters) {
		try {
			return filterByFields(mainList, filters);
		} catch (Exception e) {
			return null;
		}
	}
	

	public static List<Result> groupBy(List<?> mainList, String... fields) {
		try {
			List<Result> list = new ArrayList<Result>();
			Map<List<?>, ?> map = groupByFields(mainList, fields);
			if (map != null && map.size() > 0) {
				Set<List<?>> keys = map.keySet();
				if (keys != null && keys.size() > 0) {
					keys.forEach(currentKey -> {
						List<?> valuesOfCurrentKey = (List<?>) map.get(currentKey);
						String description = createDescriptionToResult(currentKey,
								Result.INITIAL_DESCRIPTION_GROUPED_BY, COMMA_SEPARATOR, fields);
						list.add(new Result(description, valuesOfCurrentKey));
					});
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static <T> List<T> filterByFields(List<T> mainList, List<FilterBy> filters) throws Exception {
		// Validate the mainList and Filters
		boolean isMainListValid = isMainListValid(mainList);
		boolean isFiltersValid = isFiltersValid(filters);

		if (isMainListValid && isFiltersValid) {
			List<Predicate<T>> listPredicates = new ArrayList<Predicate<T>>();

			// For each object in Main List, do another for with all filters
			for (T genericObject : mainList) {
				for (FilterBy currentFilter : filters) {
					Predicate<T> predicate = getPredicate(genericObject, currentFilter);
					listPredicates.add(predicate);
				}
			}

			// Reduce again listPredicates with an unique Predicate<T>
			Stream<Predicate<T>> streamPredicate = listPredicates.stream();
			Predicate<T> predicateReduce = streamPredicate.reduce(p -> true, Predicate::and);

			Stream<T> mainListStream = mainList.stream();
			List<T> filtered = mainListStream.filter(predicateReduce).collect(Collectors.toList());
			return filtered;

		}

		return null;

	}
	
	// Overloading of filterByFields
	private static <T> List<T> filterByFields(List<T> mainList, FilterBy... filters) throws Exception {
		return filterByFields(mainList, Arrays.asList(filters));
	}

	private static <T> Predicate<T> getPredicate(T genericObject, FilterBy filter) throws Exception {
		String field = ClassUtil.verifyIfExistsGet(filter.getField(), genericObject);
		Method method = ClassUtil.getMethodByName(field, genericObject);

		// Lists to do OR Clause
		List<Predicate<T>> predicatesToDoOrClause = new ArrayList<Predicate<T>>();

		// For each Value in filter, do a new predicate comparing a Invoked Field
		// with CurrentValue with method equals
		for (Object currentValue : filter.getValues()) {
			Predicate<T> predicateFieldEqualsValue = (obj) -> {
				try {
					Object invoked = method.invoke(obj);
					if(invoked != null)
						return invoked.equals(currentValue);
					
					return false;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			};

			// Add current Predicate in list
			predicatesToDoOrClause.add(predicateFieldEqualsValue);
		}

		// Reduce in a Unique Predicate<T>
		Stream<Predicate<T>> streamPredicateOr = predicatesToDoOrClause.stream();
		Predicate<T> predicateReduceOr = streamPredicateOr.reduce(p -> false, Predicate::or);

		return predicateReduceOr;
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

	private static String createDescriptionToResult(List<?> keys, String initialDescription, String separator, String... fields) {
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
						str += separator;
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
				Method newMethod = mainClass.getMethod(ClassUtil.verifyIfExistsGet(field, mainClass));
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
			throw new Exception("None field to group mainList");

		return true;
	}

	// Validate the Array of class FilterBy
	private static boolean isFiltersValid(List<FilterBy> filters) throws Exception {
		if (filters == null || (filters != null && filters.size() == 0))
			throw new Exception("None filter to filter mainList");

		return true;
	}

}