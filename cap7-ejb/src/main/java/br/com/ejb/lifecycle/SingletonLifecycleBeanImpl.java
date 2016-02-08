package br.com.ejb.lifecycle;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.UUID;

/**
 * Diferentemente dos EJB Stateless e Stateful, um EJB Singleton possui apenas uma instancia durante toda a execucao da aplicacao.
 * O ciclo de vida de um EJB Singleton é semelhante ao ciclo de vida dos outros tipos de EJBs, ou seja, primeiramente ele nao esta
 * carregado na memoria e nenhuma instancia existe ainda. Quando o container coloca o Singleton em servico, ele cria uma instancia
 * desse EJB que servira para todas as requisicoes de todos os clientes.
 * <hr>
 * Assim que o container cria a instancia do EJB Singleton, ele inicializa todos os ponto de injecao e invoca o metodo marcado com
 * '@PostConstruct'.
 * <hr>
 * Por padrao, a criacao da instancia de um Singleton é lazy, porém, a partir da versao 3.1 da especificacao de EJBs, é possivel informar
 * para o container criar a instancia deste EJB Singleton durante a inicializacao da aplicacao, ou seja, de forma eagle. Para tanto,
 * basta anotar o EJB Singleton com a anotacao '@Startup'.
 */
@Log
@Singleton(name = "SingletonLifecycleBeanImpl")
@Startup
public class SingletonLifecycleBeanImpl implements SingletonLifecycleBeanRemote {

	private String id;

	@PostConstruct
	void init() {
		this.id = UUID.randomUUID().toString().substring(0, 8);
		log.info(this.id + " - Singleton instance is being initialized...");
	}

	@Override
	public String createMsg() {
		return this.id + " - Hello!";
	}

	@PreDestroy
	void destroy() {
		log.info(this.id + " - Singleton instance is being destroyed...");
	}
}
