package br.com.rafawhite.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public abstract class Util implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static Double parseObjectToDouble(Object obj) {
		if (obj != null) 
			return Double.valueOf(obj.toString());
		
		return null;
	}
	
	public static Integer parseObjectToInteger(Object obj) {
		if (obj != null) 
			return Integer.valueOf(obj.toString());
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> parseArrayToList(T... array){
		return Arrays.asList(array);	
	}

}