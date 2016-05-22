package br.com.ejb.bean;

import javax.ejb.Stateless;

/**
 * Quando um EJB nao expoe uma interface de visualizacao (está anotado com @LocalBean ou
 * nao possui interfaces locais ou remotas) ele é registrado no namespace global como
 * java:global/[app-name]/module-name/bean-name![fully qualifield name of the bean].
 * Ex.: java:global/cap16-ejb/PersonBeanImpl!br.com.ejb.bean.PersonBeanImpl
 */
@Stateless
//@LocalBean
public class PersonBeanImpl implements PersonLocalBean{//, PersonRemoteBean {

	private static final long serialVersionUID = 6836565252311905366L;

	public String message() {
		return "Test";
	}
}
