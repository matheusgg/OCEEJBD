package br.com.ejb.async;

import lombok.extern.java.Log;

import javax.ejb.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * A anotacao '@Asynchronous' foi introduzida na versao 3.1 da especificacao de EJB e permite a invocacao de metodos de negocio dos EJB de forma
 * assincrona. Para tanto, é preciso que o método seja void ou retorne um Future. Para facilitar a manipulacao de operacoes assincronas, foi
 * introduzida também a classe AsyncResult que descende de Future, essa classe deve ser utilizadaa para retornar o resultado do processamento
 * assincrono.
 * <hr>
 * Como por definicao um EJB nao pode atender outras requisicoes enquanto algum de seus metodos de negocio esta sendo processado, o EJB proxy
 * realiza as chamadas dos metodos assincronos e dos metodos sincronos em objetos diferentes que estao no pool de beans.
 */
@Log
@Stateless
@Remote
public class AsyncBeanImpl implements AsyncBeanRemote {

	@Override
	public String getGreetings() {
		return "Be patient!";
	}

	/**
	 * A anotacao '@Asynchronous' pode ser aplicada em métodos ou em classes. Neste ultimo caso, todos os métodos da classe se tornarao
	 * assincronos.
	 */
	@Asynchronous
	@Override
	public Future<String> doSomeSlowTask() {
		try {
			log.info("Doing some hard work...");
			TimeUnit.SECONDS.sleep(10);
			log.info("doSomeSlowTask completed!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<>("Slow Task is done!");
	}

	@Asynchronous
	@Override
	public Future<String> doAnotherSlowTask() {
		try {
			log.info("Doing another hard work...");
			TimeUnit.SECONDS.sleep(10);
			log.info("doAnotherSlowTask completed!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AsyncResult<>("Another Slow Task is done!");
	}
}
