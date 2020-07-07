import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import br.com.rafawhite.model.People;
import br.com.rafawhite.model.Profile;
import br.com.rafawhite.util.ArrayUtil;
import br.com.rafawhite.util.ClassUtil;
import br.com.rafawhite.util.FilterBy;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Profile prof1 = new Profile("Admin", Arrays.asList("/panelAdmin", "/yourAccount"));
		Profile prof2 = new Profile("User", Arrays.asList("/yourAccount"));

		People p1 = new People("Rafael Nunes", 21, 85.2, 1.85, false);
		p1.setProfile(prof1);
		
		People p2 = new People("Lucas Ribeiro", 17, 57.2, 1.63, false);
		p2.setProfile(prof2);
		
		People p3 = new People("Club de Regatas Vasco da Gama", 121, 100, 100, true);
		p3.setProfile(prof2);
		
		People p4 = new People("Pedro Alex", 15, 53.6, 1.53, false);
		p4.setProfile(prof2);
		
		People p5 = new People("Manoel", 91, 103.1, 1.84, false);
		p5.setProfile(prof2);
		
		People p6 = new People("Leandro", 15, 61.3, 1.63, false);
		p6.setProfile(prof1);
		
		List<People> peoples = Arrays.asList(p1, p2, p3, p4, p5, p6);

		FilterBy filterProfileUser = new FilterBy("profile.description", "Admin");	
		List<People> resultsFiltered = (List<People>) ArrayUtil.filterBy(peoples, filterProfileUser);
		System.out.println(resultsFiltered);
		

	}

}
