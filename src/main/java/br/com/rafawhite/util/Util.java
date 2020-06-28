package br.com.rafawhite.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Util implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Integer DEFAULT_SCALE = 4;
	private static final RoundingMode DEFAULT_ROUDING_MODE = RoundingMode.HALF_EVEN;

	// Divide
	public static BigDecimal divide(Number smaller, Number total) {
		boolean numbersAreValid = validateNumbersToDivide(smaller, total);
		if (numbersAreValid) {
			BigDecimal bigDecimalSmaller = new BigDecimal(smaller.toString());
			BigDecimal bigDecimalTotal = new BigDecimal(total.toString());
			BigDecimal bigDecimalResult = bigDecimalSmaller.divide(bigDecimalTotal, DEFAULT_SCALE, DEFAULT_ROUDING_MODE);
			return bigDecimalResult;
		}
		return new BigDecimal("0.0");
	}

	// Divide Overloading One
	public static BigDecimal divide(Number smaller, Number total, RoundingMode roudingMode) {
		boolean numbersAreValid = validateNumbersToDivide(smaller, total);
		if (numbersAreValid) {
			BigDecimal bigDecimalSmaller = new BigDecimal(smaller.toString());
			BigDecimal bigDecimalTotal = new BigDecimal(total.toString());
			BigDecimal bigDecimalResult = bigDecimalSmaller.divide(bigDecimalTotal, DEFAULT_SCALE, roudingMode);
			return bigDecimalResult;
		}
		return new BigDecimal("0.0");
	}

	// Divide Overloading Two
	public static BigDecimal divide(Number smaller, Number total, int scale, RoundingMode roudingMode) {
		boolean numbersAreValid = validateNumbersToDivide(smaller, total);
		if (numbersAreValid) {
			BigDecimal bigDecimalSmaller = new BigDecimal(smaller.toString());
			BigDecimal bigDecimalTotal = new BigDecimal(total.toString());
			BigDecimal bigDecimalResult = bigDecimalSmaller.divide(bigDecimalTotal, scale, roudingMode);
			return bigDecimalResult;
		}
		return new BigDecimal("0.0");
	}

	// Calculate the Percent
	public static BigDecimal calcularPorcentagem(Number smaller, Number total) {
		BigDecimal bigDivideResult = divide(smaller, total);
		if (bigDivideResult != null) {
			return bigDivideResult.multiply(new BigDecimal("100"));
		}
		return bigDivideResult;
	}
	
	// Return true if the numbers are valide to divide 
	private static boolean validateNumbersToDivide(Number smaller, Number total) {
		if (smaller != null && total != null && total.doubleValue() > 0.0) 
			return true;
		
		return false;
	}

}
