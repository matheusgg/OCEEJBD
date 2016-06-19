package br.com.ejb.interceptors;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.interceptor.InvocationContext;
import java.util.UUID;
import java.util.logging.Level;

/**
 * O ciclo de vida de um interceptor esta extremamente relacionado ao ciclo de vida do objeto alvo, ou seja, para cada EJB interceptado,
 * uma instancia do interceptor sera criada.
 * <p>
 * Como os interceptors estao na mesma stack do EJB alvo, eles tambem sao criados, destruidos e passivados, assim como o EJB alvo.
 */
@Log
public class LifecycleInterceptor {

	public LifecycleInterceptor() {
		log.info(UUID.randomUUID().toString());
	}

	/**
	 * É possivel interceptar eventos de ciclo de vida utilizando Interceptors. Para isso, basta especificar um metodo:
	 * <ul>
	 * <li>Com qualquer visibilidade;</li>
	 * <li>Que receba um parametro do tipo javax.interceptor.InvocationContext;</li>
	 * <li>Que nao declare excecoes;</li>
	 * <li>Esteja anotado com o evento que se deseja interceptar;</li>
	 * </ul>
	 * <p>
	 * Mesmo se nao houver metodos de ciclo de vida dentro do EJB alvo, o interceptor sera invocado.
	 */
	@PostConstruct
	void prePostConstruct(final InvocationContext context) {
		try {
			log.info("========================= INTERCEPTING POST CONSTRUCT LIFECYCLE EVENT =========================");
			log.info("Target Bean: " + context.getTarget());

			/**
			 * Como pode nao haver metodos de ciclo de vida dentro do EJB alvo, o metodo InvocationContext.getMethod()
			 * sempre retornara nulo durante a execucao de um interceptor de eventos de ciclo de vida.
			 */
			log.info("Invoked Method: " + context.getMethod());

			/**
			 * Como lifecycle methods nao recebem parametros, a chamada ao metodo InvocationContext.getParameters()
			 * resultara em uma excecao.
			 */
			// log.info("Parameters: " + Arrays.asList(context.getParameters()));

			log.info("Context Data: " + context.getContextData());
			log.info("Timer: " + context.getTimer());

			/**
			 * O metodo InvocationContext.proceed() deve ser chamado para o metodo de ciclo de vida ser invocado.
			 */
			context.proceed();
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
