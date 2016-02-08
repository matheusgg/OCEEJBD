package br.com.ejb.calculator;

/**
 * Created on 21/01/2016.
 */
public interface CalculatorRemoteBusiness {

	int sum(int... numbers);

	int subtract(int... numbers);

}
