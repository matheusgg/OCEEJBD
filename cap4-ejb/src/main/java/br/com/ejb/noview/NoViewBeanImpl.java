package br.com.ejb.noview;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * A partir da versao 3.1 da especificacao de EJBs, nao é mais obrigatorio a especificacao de interfaces remotas ou locais. Caso um EJB
 * nao especifique nenhuma interface, por padrao, a classe de implementacao do EJB sera utilziada como uma interface local. Se a anotacao
 * '@LocalBean' for especificada, significa que este EJB expoe uma no no-interface view, ou seja, ele podera ser acessado apenas localmente.
 * O uso dessa anotacao é equivalente a especificacao de nenhuma view.
 */
@Stateless
@LocalBean
public class NoViewBeanImpl {

	public String createMsg() {
		return "Test message!";
	}

}
