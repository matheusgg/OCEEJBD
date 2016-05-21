package br.com.ejb.jndi;

import br.com.ejb.bean.PersonLocalBean;
import lombok.extern.java.Log;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A especificacao de EJB 3.1 define um namespace JNDI global que deve ser seguido por todos os containers EJB que implementam a especificacao.
 * Esse contexto global possui o seguinte formato:
 * <p>
 * java:global/[app-name]/module-name/bean-name![fully qualifield name of the view interface]
 * <p>
 * As partes entre colchetes sao opcionais, ou seja app-name (nome do EAR) e o nome totalmente qualificado da interface de visualizacao
 * nao sao obrigatorias. Desta forma, toda vez que um EJB é implantado em um container que segue a especificacao EJB 3.1, este container deve
 * inserir este EJB no namespace JNDI global. O nome do modulo (module-name) é o nome do JAR ou do WAR onde o EJB esta contido. Caso a aplicacao
 * seja empacotada como um EAR, o app-name deve ser informado.
 * <hr>
 * A anotacao @EJB serve para referenciar um outro EJB e inserir essa referencia dentro do ENC do bean que declara essa anotacao.
 */
@Log
@Stateless
@EJB(name = "reference/ejb/PersonBean", beanInterface = PersonLocalBean.class)
public class JndiBeanImpl implements JndiRemoteBean {

	private static final long serialVersionUID = -4893545229040960801L;

	@Override
	public void execute() throws NamingException {
		/*
		 * A referencia para o SessionContext pode ser obtida atraves de injecao (@Resource) ou atraves do lookup JNDI sobre o nome
		 * java:comp/EJBContext.
		 */
		final SessionContext sessionContext = InitialContext.doLookup("java:comp/EJBContext");

		/*
		 * Global JNDI
		 */
		PersonLocalBean personBean = InitialContext.doLookup("java:global/cap16-ejb/PersonBeanImpl"); //ou java:global/cap16-ejb/PersonBeanImpl!br.com.ejb.bean.PersonLocalBean
		log.info(personBean.toString());

		/*
		 * JNDI ENC
		 * É possivel recuperar as referencias inseridas no ENC atraves do lookup do SessionContext ou
		 * atraves do namespace java:comp/env (que corresponde ao componente atual, ou seja, ao ENC do EJB atual).
		 * O lookup do SessionContext nao precisa que seja informado o namespace java:comp/env/.
		 */
		personBean = (PersonLocalBean) sessionContext.lookup("reference/ejb/PersonBean");
		log.info(personBean.toString());

		personBean = InitialContext.doLookup("java:comp/env/reference/ejb/PersonBean");
		log.info(personBean.toString());
	}
}
