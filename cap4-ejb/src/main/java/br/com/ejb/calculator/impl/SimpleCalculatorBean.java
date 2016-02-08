package br.com.ejb.calculator.impl;

import br.com.ejb.calculator.CalculatorCommomBusiness;
import br.com.ejb.calculator.CalculatorRemoteBusiness;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * A anotacao LocalBean indica para o container que este EJB expõe uma no-interface view, ou seja, ele não
 * poderá ser acessado remotamente.
 * <hr>
 * As views são as interfaces onde este EJB será exposto, neste caso, com a utilizacao da anotação @LocalBean,
 * este EJB não será exposto. Mas se a anotação @Remote for especificada, significa que este EJB possui uma view
 * remota.
 * <hr>
 * Quando um EJB possui uma interface (view) remota, no JBoss/Wildfly ele sempre será associado ao prefixo JNDI java:jboss/exported.
 * <hr>
 * A anotação @Remote pode ser aplicada de 3 maneiras diferentes:
 * <ul>
 * <li>
 * Na implementação do EJB sem especificar atributos. Neste caso, o EJB deve implementar pelo menos uma interface e esta será utilizada
 * como a view remota do mesmo (Caso nenhuma interface seja especificada, uma exceção durante o deployment será lançada);
 * </li>
 * <li>
 * Na(s) interface(s) que este EJB implementa, desta forma, este bean possuirá uma, ou várias, views, uma para cada interface implementada;
 * </li>
 * <li>
 * Na implementação do EJB especificando as interfaces que serão utilizadas como view para este bean.
 * </li>
 * </ul>
 * <br>
 * Se a anotação @Remote for especificada com interfaces e o EJB não implementá-las, ele deve pelo menos possuir métodos com a mesma assinatura daqueles
 * declarados nas interfaces, caso contrário, qualquer chamada remota para um método que exista na interface mas não está declarado na implementação
 * do EJB resultará em um erro na invocação.
 * <br>
 * Caso a anotação @Remote especifique as interfaces e o EJB já implemente alguma outra interface, apenas as interfaces declaradas na anotação @Remote serão
 * utilizadas como view deste EJB.
 */
@Stateless
//@LocalBean
@Remote(CalculatorRemoteBusiness.class)
public class SimpleCalculatorBean implements CalculatorCommomBusiness {

	//	@Override
	public int sum(int... numbers) {
		int sum = 0;
		for (int num : numbers) {
			sum += num;
		}
		return sum;
	}

	@Override
	public int multiply(int... numbers) {
		int sum = 0;
		for (int num : numbers) {
			sum *= num;
		}
		return sum;
	}
}
