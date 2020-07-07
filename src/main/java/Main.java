import java.util.Arrays;
import java.util.List;

import br.com.rafawhite.model.People;
import br.com.rafawhite.model.Profile;
import br.com.rafawhite.util.ArrayUtil;
import br.com.rafawhite.util.FilterBy;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Profile admin = new Profile("Admin", Arrays.asList("/panelAdmin", "/panelManager", "/yourAccount"));
		Profile manager = new Profile("Manager", Arrays.asList("/panelManager", "/yourAccount"));
		Profile user = new Profile("User", Arrays.asList("/yourAccount"));
		Profile anonymous = new Profile("Anonymous", Arrays.asList("/yourAccount"));

		People p1 = new People("Rafael Nunes", 21, 85.2, 1.85, false);
		p1.setProfile(admin);
		
		People p2 = new People("Lucas Ribeiro", 17, 57.2, 1.63, false);
		p2.setProfile(manager);
		
		People p3 = new People("Club de Regatas Vasco da Gama", 121, 100, 100, true);
		p3.setProfile(user);
		
		People p4 = new People("Pedro Alex", 15, 53.6, 1.53, false);
		p4.setProfile(user);
		
		People p5 = new People("Manoel", 91, 103.1, 1.84, false);
		p5.setProfile(user);
		
		People p6 = new People("?", 0, 0, 0, false);
		p6.setProfile(anonymous);
		
		List<People> peoples = Arrays.asList(p1, p2, p3, p4, p5, p6);

		FilterBy filterProfileUser = new FilterBy("profile.description", "Admin", "Anonymous");	
		List<People> resultsFiltered = (List<People>) ArrayUtil.filterBy(peoples, filterProfileUser);
		System.out.println(resultsFiltered);
		

	}

}
