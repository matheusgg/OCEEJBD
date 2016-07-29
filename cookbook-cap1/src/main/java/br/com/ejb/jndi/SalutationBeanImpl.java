package br.com.ejb.jndi;

import javax.ejb.Stateless;

/**
 * Caso um EJB implemente mais de uma interface, porem nao declare as anotacoes @Local ou @Remote e nenhuma das
 * interfaces implementadas possua essas duas anotacoes, o container EJB nao realizara nenhum binding para este EJB,
 * ou seja, este EJB nao sera inserido no contexto JNDI, nem mesmo como um no-interface-view bean.
 * <p>
 * Caso um EJB possua apenas uma interface (seja local ou remota), ele podera ser acessado atraves dessa interface
 * ou diretamente. Ex.: java:global/cookbook-cap1/SalutationBeanImpl!br.com.ejb.jndi.SalutationRemoteBean ou
 * java:global/cookbook-cap1/SalutationBeanImpl. Porém, caso o EJB possua mais do que uma interface, ele nao podera
 * ser acessado diretamente, ou seja, ele devera ser acessado atraves de alguma das interfaces que ele expoe. Ex.:
 * java:global/cookbook-cap1/SalutationBeanImpl!br.com.ejb.jndi.SalutationRemoteBean ou
 * java:global/cookbook-cap1/SalutationBeanImpl!br.com.ejb.jndi.SalutationLocalBean
 */
@Stateless
public class SalutationBeanImpl implements SalutationRemoteBean, SalutationLocalBean {

	@Override
	public String getInformalGreetings() {
		return "Hi user!";
	}

	@Override
	public String getFormalGreetings() {
		return "Dear user";
	}
}
