package br.com.ejb.xml;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 * Quando o container EJB é inicializado, ele recupera todas as informacoes do arquivo ejb-jar.xml (caso exista), inclusive os valores dos elementos
 * env-entry, que sao colocados no contexto privado do EJB, ou seja, o EJB ENC, para serem recuperados de forma automatica (via @Resource annotation),
 * ou manual, via SessionContext.lookup.
 * <hr>
 * A partir da versao 3.1 da especificacao de EJBs, é possivel empacotar EJBs dentro de modulos WARs e incluir o arquivo ejb-jar.xml, porém este
 * último deve ser colocado dentro da pasta WEB-INF.
 */
@Stateless(name = "EJBDeploymentDescriptorBean")
@Remote
public class EJBDeploymentDescriptorBeanImpl implements EJBDeploymentDescriptorBeanRemote {

	@Resource
	private SessionContext sessionContext;

	/**
	 * É possivel recuperar os valores declarados nos elementos env-entry do ejb-jar.xml para inicializar propriedades. Basta anotar o campo com
	 * '@Resource' e especificar o nome do elemento que se deseja recuperar.
	 */
	@Resource(name = "param1")
	private String param1;

	private String param2;

	@PostConstruct
	void init() {
		/*
		 * Também é possivel recuperar os valores de env-entry de forma programatica atraves do lookup dos valores do EJB ENC.
		 */
		this.param2 = (String) this.sessionContext.lookup("param2");
	}

	@Override
	public String createMsg() {
		return this.param1 + " - " + this.param2;
	}
}
