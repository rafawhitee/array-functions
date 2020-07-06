import java.util.Arrays;
import java.util.List;

import br.com.rafawhite.model.People;
import br.com.rafawhite.util.ArrayUtil;
import br.com.rafawhite.util.FilterBy;

public class Main {

	public static void main(String[] args) {

		People p1 = new People("Rafael Nunes", 21, 85.2, 1.85, false);
		People p2 = new People("Lucas Ribeiro", 17, 57.2, 1.63, false);
		People p3 = new People("Club de Regatas Vasco da Gama", 121, 100, 100, true);
		People p4 = new People("Pedro Alex", 15, 53.6, 1.53, false);
		People p5 = new People("Manoel", 91, 103.1, 1.84, false);
		People p6 = new People("Leandro", 15, 61.3, 1.63, false);
		
		List<People> peoples = Arrays.asList(p1, p2, p3, p4, p5, p6);

		FilterBy filterAge = new FilterBy("age", 20, 15);
		FilterBy filterOnlyLegalPerson = new FilterBy("legalPerson", true);
		
		List<People> resultsFiltered = ArrayUtil.filterBy(peoples, filterOnlyLegalPerson);
		System.out.println(resultsFiltered);

	}

}
