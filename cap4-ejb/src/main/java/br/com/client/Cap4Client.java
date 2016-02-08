package br.com.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * java:global/cap4-ejb/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness
 * java:app/cap4-ejb/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness
 * java:module/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness
 * java:jboss/exported/cap4-ejb/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness
 * java:global/cap4-ejb/SimpleCalculatorBean
 * java:app/cap4-ejb/SimpleCalculatorBean
 * java:module/SimpleCalculatorBean
 */
public class Cap4Client {

	/**
	 * Quando um EJB é exposto com uma view (interface) remota, ele sempre será associado ao
	 * prefixo JNDI java:jboss/exported.
	 * <hr>
	 * A especificação de Enterprise Java Beans não determina um mecanismo padronizado de implantação
	 * de EJBs, deste modo, cada servidor de aplicação possui seu própio um mecanismo de implantação.
	 * <hr>
	 * Utilizando o protocolo remote do JBoss ou http-remoting do Wildfly para acessar EJBs não é
	 * necessário inserir o prefixo java:jboss/exported no nome JNDI do recurso remoto.
	 * Se este prefixo for especificado o Jboss/Wildfly não o removerá e concatenará novamente,
	 * ficando, por exemplo, java:jboss/exported/java:jboss/exported/cap4-ejb/Simple...
	 * deste modo, o EJB solicitado não será localizado pelo serviço de nomes JNDI.
	 */
	public static void main(String[] args) throws NamingException {
		/*
		 * Configurações de acesso remoto do JBoss AS 7.
		 */
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		Context context = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * Simple Stateless EJB
		 * ======================================================================================================================================
		 */
		/*
		 * Esta linha resultará em uma exceção, pois a view deste EJB é do tipo CalculatorRemoteBusiness, desta forma,
		 * não é possível realizar o cast do proxy EJB retornado para CalculatorCommomBusiness.
		 */
		// CalculatorCommomBusiness calculatorCommomBusiness = (CalculatorCommomBusiness) context.lookup("cap4-ejb/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness");
		// System.out.println(calculatorCommomBusiness.multiply(2, 2));
		// System.out.println(calculatorCommomBusiness.sum(10, 10, 3));

		// CalculatorRemoteBusiness calculatorRemoteBusiness = (CalculatorRemoteBusiness) context.lookup("cap4-ejb/SimpleCalculatorBean!br.com.ejb.calculator.CalculatorRemoteBusiness");
		//System.out.println(calculatorRemoteBusiness.sum(10, 10, 3));

		/*
		 * Esta linha resultará em uma exceção, pois apesar da interface CalculatorRemoteBusiness possuir o método
		 * subtract, a implementação do EJB (SimpleCalculatorBean) não possui este método, já que não implementa a
		 * interface CalculatorRemoteBusiness e nem possui um método com a mesma assinatura do método subtract
		 * declarado em CalculatorRemoteBusiness.
		 */
		// System.out.println(calculatorRemoteBusiness.subtract(10, 10));

		/*
		 * ======================================================================================================================================
		 * No view Bean
		 * ======================================================================================================================================
		 */
		// Esta chamada resultara em uma excecao, pois o EJB NoViewBeanImpl nao expoe uma view Remota.
		/*NoViewBeanImpl noViewBean = (NoViewBeanImpl) context.lookup("cap4-ejb/NoViewBeanImpl!br.com.ejb.noview.NoViewBeanImpl");
		System.out.println(noViewBean.createMsg());*/
	}

}
