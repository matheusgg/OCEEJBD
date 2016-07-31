package br.com.ejb.singleton.dependson;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;

/**
 * Por padrao, singletons sao inicializados de forma lazy.
 * <p>
 * A anotacao @DependsOn introduzida na versao 3.1 da especificacao de EJBs serve para especificar os
 * nomes dos EJBs que devem ser inicializados antes deste bean.
 * <p>
 * A anotacao @DependsOn deve ser aplicada apenas em EJBs singleton, caso esta anotacao seja utilizada
 * com outro tipo de EJB ela nao tera efeito.
 * <p>
 * Quando algum metodo de negocio do SingletonBean2 for invocado pela primeira vez, o container EJB
 * inicializara os EJBs SingletonBean1 e SingletonBean3 antes de inicializar este bean. Somente
 * após isso que o método solicitado sera invocado.
 * <p>
 * Caso a inicializacao dos singletons seja feita de forma eagle (atraves da anotacao @Startup), a mesma
 * logica sera aplicada, ou seja, os beans SingletonBean1 e SingletonBean3 serao inicializados antes
 * do SingletonBean2 no startup da aplicacao, ou seja, mesmo SingletonBean1 e SingletonBean3
 * nao possuindo a anotacao @Startup, eles serao inicializados no startup da aplicacao por causa
 * do SingletonBean2.
 * <p>
 * A ordem de inicializacao dos singletons especificados na anotacao @DependsOn não é garantida, caso
 * este comportamento de ordem de inicializacao seja necessario, a anotacao @DependsOn deve ser aplicada
 * nos outros EJBs.
 */
@Log
@Singleton
//@Startup
@DependsOn({"SingletonBean1", "SingletonBean3"})
public class SingletonBean2 {

	@PostConstruct
	void init() {
		log.info("Initializing SingletonBean2...");
	}

	public String getMessage() {
		return "Message from SingletonBean2";
	}

}
