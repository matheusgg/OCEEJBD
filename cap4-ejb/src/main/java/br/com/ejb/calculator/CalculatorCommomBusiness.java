package br.com.ejb.calculator;

/**
 * Caso a anotação @Remote seja especificada na interface implementada pelo EJB e o mesmo não declarar essa anotação,
 * esta interface será exporta como view do EJB que a implementa, mesmo se o atributo da anotação @Remote for especificado.
 * Neste caso, o atributo value de @Remote foi informado, porém ele será ignorado, deixando a interface CalculatorCommomBusiness
 * como view dos EJBs que a implementar.
 */
//@Remote(CalculatorRemoteBusiness.class)
public interface CalculatorCommomBusiness {

	int sum(int... numbers);

	int multiply(int... numbers);

}
