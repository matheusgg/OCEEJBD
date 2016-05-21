package br.com.ejb.resource;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A anotacao @Resource pode ser utilizada tanto na classe, quanto em fields e métodos setters.
 * Quando esta anotacao é utilizada a nivel de classe, o atributo name deve ser especificado.
 */
@Log
@Stateless
@Resource(name = "defaultCF", lookup = "java:/ConnectionFactory")
public class ResourceBeanImpl implements ResourceRemoteBean {

	private static final long serialVersionUID = -3360556257935716313L;

	/**
	 * A injecao atraves da anotacao @Resource é similar a anotacao @EJB. A referencia injetada é inserida no ENC do bean
	 * que possui o ponto de injecao.
	 * <p>
	 * O atributo lookup é semelhante ao atributo mappedName, porém lookup esta definido na especificacao e pode ser
	 * utilizado para referenciar qualquer objeto que esteja no contexto global (java:global).
	 * <p>
	 * Assim como acontece com a anotacao @EJB, quando o atributo name nao é especificado, o nome da referencia inserida
	 * no ENC é obtido atraves do nome totalmente qualificado da classe que possui o ponto de injecao seguido pelo nome
	 * do atributo injetado. Ex.: br.com.ejb.resource.ResourceBeanImpl/factory1
	 */
	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory factory1;

	@Resource(name = "cf", lookup = "java:/ConnectionFactory")
	private ConnectionFactory factory2;

	@Override
	public void execute() throws NamingException {
		/*
		 * Annotation Injection
		 */
		log.info(this.factory1.toString());

		/*
		 * Recuperando a referencia inserida no ENC atraves da injecao por anotacao.
		 */
		log.info(InitialContext.doLookup("java:comp/env/br.com.ejb.resource.ResourceBeanImpl/factory1").toString());
		log.info(InitialContext.doLookup("java:comp/env/cf").toString());

		/*
		 * Recuperando a referencia inserida no ENC atraves da declaracao de referencia na classe.
		 */
		log.info(InitialContext.doLookup("java:comp/env/defaultCF").toString());
	}
}
