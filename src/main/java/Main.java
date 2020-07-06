import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.rafawhite.model.People;
import br.com.rafawhite.util.ArrayUtil;
import br.com.rafawhite.util.FilterBy;
import br.com.rafawhite.util.Result;

public class Main {

	public static void main(String[] args) {

		People p1 = new People("Rafael 1", 20, 85.2, 1.84);
		People p2 = new People("Lucas", 17, 57.2, 1.63);
		People p3 = new People("Rebekah", 20, 55.2, 1.62);
		People p4 = new People("Pedro 1", 15, 53.6, 1.53);
		People p5 = new People("Leandro", 49, 89.1, 1.76);
		People p6 = new People("Rafael 2", 21, 53.6, 1.53);
		People p7 = new People("Pedro 2", 15, 53.6, 1.53);
		People p8 = new People("Pedro 3", 15, 53.6, 1.53);
		People p9 = new People("Rafael 3", 20, 85.2, 1.84);
		List<People> peoples = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9);

//       Predicate<People> predicateAgeEqual21 = (people) -> people.getAge() == 21;
//       Predicate<People> predicateAgeEqual15 = (people) -> people.getAge() == 15;
//       List<Predicate<People>> listPredicatesPeople = Arrays.asList(predicateAgeEqual21, predicateAgeEqual15);
//       Predicate<People> predicateOr = predicateAgeEqual15.or(predicateAgeEqual21);
//       Predicate<People> predicateOr2 = listPredicatesPeople.stream().reduce(p -> false, Predicate::or);

//       List<People> filteredOne = peoples.stream().filter(predicateOr2).collect(Collectors.toList());
//       System.out.println(filteredOne);
		FilterBy filterAge = new FilterBy("age", 20, 15);
		FilterBy filterName = new FilterBy("name", "Rafael 1");

		List<People> resultsFiltered = ArrayUtil.filterBy(peoples, filterAge, filterName);
		System.out.println(resultsFiltered);

	}

}
