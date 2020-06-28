import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.rafawhite.model.People;
import br.com.rafawhite.util.ArrayFunctions;

public class Main {

	public static void main(String[] args) {
       People p1 = new People("Rafael", 20, 85.2, 1.84);
       People p2 = new People("Lucas", 17, 57.2, 1.63);
       People p3 = new People("Rebekah", 20, 55.2, 1.62);
       People p4 = new People("Pedro", 15, 53.6, 1.53);
       People p5 = new People("Leandro", 49, 89.1, 1.76);
       People p6 = new People("Vasco", 15, 53.6, 1.53);
       People p7 = new People("Pedro", 15, 53.6, 1.53);
       People p8 = new People("Pedro", 15, 53.6, 1.53);
       List<People> peoples = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8);
       
       try {
    	   Map<List<?>, ?> retorno = ArrayFunctions.groupByFields(peoples, "age", "weight");
    	   Set<List<?>> keys = retorno.keySet();
    	   keys.forEach(currentKey -> {
    		   System.out.println(retorno.get(currentKey));
    	   });
       }catch(Exception e) {
    	   e.printStackTrace();
       }
       
	}

}
