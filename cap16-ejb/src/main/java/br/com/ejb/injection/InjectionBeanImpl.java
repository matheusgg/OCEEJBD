package br.com.ejb.injection;

import br.com.ejb.bean.PersonLocalBean;
import lombok.extern.java.Log;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Log
@Stateless
public class InjectionBeanImpl implements InjectionRemoteBean {

	private static final long serialVersionUID = 8180205245100260931L;

	/**
	 * A anotacao @EJB tambem serve para injetar referencias em fields ou métodos setters.
	 * <p>
	 * Quando um bean é injetado atraves da anotacao @EJB, uma referencia também é inserida no ENC
	 * do EJB que declara o ponto de injecao. O nome da referencia inserida no ENC é extraido do
	 * atributo name da anotacao @EJB, caso o atributo nao seja especificado, o nome da referencia é
	 * extraido do nome totalmente qualificado da classe que possui o campo injetado seguido pelo
	 * nome do campo ou método setter. Ex.: br.com.ejb.injection.InjectionBeanImpl/personBean.
	 * <p>
	 * Quando existe ambiguidade de nomes de EJBs, ou seja, dois beans com o mesmo nome, porém em diferentes
	 * JARs dentro de um mesmo EAR, a especificacao EJB define um padrao de nomenclatura para referenciar o
	 * bean correto desejado pelo nome:
	 * nomedojar.jar#NomeDoBean
	 * Ex.: core-ejb.jar#EmployeeBeanImpl
	 * Desta forma, é possivel utilizar a anotacao @EJB da seguinte forma: @EJB(beanName = "nomedojar.jar#NomeDoBean").
	 */
	@EJB
	//	@EJB(name = "Person")
	private PersonLocalBean personBean;

	@Override
	public void execute() throws NamingException {
		/*
		 * Annotation Injection
		 */
		log.info(this.personBean.toString());

		/*
		 * Recuperando a referencia injetada no campo personBean que tambem foi inserida no ENC deste bean
		 * com o nome br.com.ejb.injection.InjectionBeanImpl/personBean.
		 */
		log.info(InitialContext.doLookup("java:comp/env/br.com.ejb.injection.InjectionBeanImpl/personBean").toString());
		// log.info(InitialContext.doLookup("java:comp/env/Person").toString());
	}

	/**
	 * Mesmo que o metodo setter seja privado, o container EJB ainda assim injetara a referencia do bean.
	 */
	/*@EJB
	private void setPersonLocalBean(final PersonLocalBean personBean) {
		this.personBean = personBean;
	}*/
}
