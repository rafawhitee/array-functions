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

import br.com.rafawhite.dto.FilterBy;
import br.com.rafawhite.dto.GroupResult;
import br.com.rafawhite.factory.ComparatorFactory;
import br.com.rafawhite.factory.ValidatorFactory;
import br.com.rafawhite.interfaces.Comparator;
import br.com.rafawhite.interfaces.Validator;

public abstract class ArrayUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String AND = "AND";
	public static final String OR = "AND";
	public static final String COMMA_SEPARATOR = ",";

	// Intermediate Method
	// Do the groupByFields that returns a Map<List>?, ?>
	// Get this return Map and parse do List<GroupResult> with a Description and
	// List<?> values
	public static Map<List<?>, ?> groupByWithMapReturn(List<?> mainList, String... fields) {
		try {
			return groupByFields(mainList, fields);		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Intermediate Method
	// Do the groupByFields that returns a Map<List>?, ?>
	// Get this return Map and parse do List<GroupResult> with a Description and
	// List<?> values
	public static List<GroupResult> groupBy(List<?> mainList, String... fields) {
		try {
			List<GroupResult> list = new ArrayList<GroupResult>();
			Map<List<?>, ?> map = groupByWithMapReturn(mainList, fields);
			if (map != null && map.size() > 0) {
				Set<List<?>> keys = map.keySet();
				if (keys != null && keys.size() > 0) {
					keys.forEach(currentKey -> {
						List<?> valuesOfCurrentKey = (List<?>) map.get(currentKey);
						String description = createDescriptionToResult(currentKey,
								GroupResult.INITIAL_DESCRIPTION_GROUPED_BY, COMMA_SEPARATOR, fields);
						list.add(new GroupResult(description, valuesOfCurrentKey));
					});
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> filterByFields(List<?> mainList, List<FilterBy> filters) throws Exception {
		// Validate the mainList and Filters
		boolean isMainListValid = isMainListValid(mainList);
		boolean isFiltersValid = isFiltersValid(filters);

		if (isMainListValid && isFiltersValid) {
			List<Predicate<?>> listPredicates = new ArrayList<Predicate<?>>();

			// For each object in Main List, do another for with all filters
			for (Object genericObject : mainList) {
				for (FilterBy currentFilter : filters) {
					Predicate<?> predicate = getPredicate(genericObject, currentFilter);
					listPredicates.add(predicate);
				}
			}

			// Reduce again listPredicates with an unique Predicate<T>
			Stream<Predicate<?>> streamPredicate = listPredicates.stream();
			Predicate<?> predicateReduce = streamPredicate.reduce(p -> true, Predicate::and);

			Stream mainListStream = mainList.stream();
			List<?> filtered = (List<?>) mainListStream.filter(predicateReduce).collect(Collectors.toList());
			return filtered;

		}

		return null;

	}

	// Overloading of filterByFields
	public static List<?> filterByFields(List<?> mainList, FilterBy... filters) throws Exception {
		return filterByFields(mainList, Arrays.asList(filters));
	}

	private static Predicate<?> getPredicate(Object genericObject, FilterBy filter) throws Exception {
		final List<Method> methods = ClassUtil.getChainGetters(filter.getField(), genericObject.getClass());

		// Lists to do OR Clause
		List<Predicate<?>> predicatesToDoOrClause = new ArrayList<Predicate<?>>();

		// For each Value in filter, do a new predicate comparing a Invoked Field
		// with CurrentValue with method equals
		for (Object currentValue : filter.getValues()) {

			// Predicate to check equals of last method on List<Method> methods
			Predicate<?> predicateFieldEqualsValue = (obj) -> {

				// Start with the parameter Obj
				Object penultimateInvoked = obj;

				// Start lastMethod with null
				Method lastMethod = null;

				int count = 0;
				// Do a for each in Methods
				for (final Method currentMethod : methods) {
					try {
						// Invoke the current
						Object currentInvoked = currentMethod.invoke(penultimateInvoked);

						// Check if the invoke is != null
						if (currentInvoked != null) {
							// Is is != null, check the count if count+1 != methods.size()
							// Because penultimateInvoked can't be the last invoked
							if ((count + 1) != methods.size()) {
								penultimateInvoked = currentInvoked;
							}
							// The method has to be the Last
							lastMethod = currentMethod;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					count++;
				}

				// If last Method and penultimateInvoked same != null
				if (lastMethod != null && penultimateInvoked != null) {
					try {
						// Check invoke LastMethod with penultimateInvoked if equals currentValue
						// of FIRST FOR EACH = for (Object currentValue : filter.getValues())
						Object invokeToCheck = lastMethod.invoke(penultimateInvoked);

						// Compare currentValue, ComparatorType and InvokedValue
						Comparator comparator = ComparatorFactory.create(filter);
						boolean compare = comparator.compare(currentValue, invokeToCheck);
						return compare;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return false;

			};

			// Add current Predicate in list
			predicatesToDoOrClause.add(predicateFieldEqualsValue);
		}

		// Reduce in a Unique Predicate<T>
		Stream<Predicate<?>> streamPredicateOr = predicatesToDoOrClause.stream();
		Predicate<?> predicateReduceOr = streamPredicateOr.reduce(p -> false, Predicate::or);

		return predicateReduceOr;
	}

	// Do the grouping
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			Stream stream = mainList.stream();

			// Call createFunctionWithAllMethodsInvoked to create a Function with all
			// methods invoked
			Function<Object, List<Object>> compositeKey = createFunctionWithAllMethodsInvoked(methods);

			// Use the collect of Stream to groupingBy compositeKey and the returns a List
			grouped = (Map<List<?>, ?>) stream.collect(Collectors.groupingBy(compositeKey, Collectors.toList()));

		}

		// Returns the Map
		return grouped;
	}

	// Create description to GroupResult
	private static String createDescriptionToResult(List<?> keys, String initialDescription, String separator,
			String... fields) {
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
		Function<Object, List<Object>> compositeKey = (obj) -> {
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
				if (newMethod != null && !methods.contains(newMethod))
					methods.add(newMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return methods;
	}

	/* VALIDATORS */

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

		// Do a for to valid each Filter
		// Valid the values with ComparatorType
		for (FilterBy filter : filters) {
			Validator currentValidator = ValidatorFactory.getValidator(filter);
			boolean currentFilterIsValid = currentValidator.validate(filter);
			if (!currentFilterIsValid) {
				throw new Exception("The filter " + filter.getField() + " with comparator "
						+ filter.getComparator().getType() + " is not valid");
			}
		}

		return true;
	}

}