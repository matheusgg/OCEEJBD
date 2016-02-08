package br.com.ejb.concurrency.singleton.cmc;

import br.com.ejb.concurrency.ConcurrencyBeanRemote;

import javax.ejb.*;
import java.util.concurrent.TimeUnit;

/**
 * Por padrao, o acesso concorrente a um EJB Singleton é controlado pelo container, ou seja, é utilizada a estrategia Container Managed Concurrency (CMC).
 * A Anotacao '@ConcurrencyManagement' é utilizada para especificar se esse acesso deve ser controlado pelo container (ConcurrencyManagementType.CONTAINER)
 * ou pelo desenvolvedor (ConcurrencyManagementType.BEAN). Quando essa anotacao nao é especificada no EJB Singleton, o valor ConcurrencyManagementType.CONTAINER
 * é utilizado por padrao.
 * <hr>
 * A anotacao '@AccessTimeout' pode ser especificada tanto na classe como no metodo. Caso seja especificada nos dois pontos, o metodo sempre leva precedencia.
 * Caso o valor do atributo value seja > 0, as threads esperarao este tempo definido até lancarem uma excecao.
 * Caso o valor do atributo value seja = 0, significa que este método nao permite acesso concorrente.
 * Caso o valor do atributo value seja = -1, significa que as threads esperarao por um tempo indeterminado até adquirirem o lock.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(value = 20, unit = TimeUnit.SECONDS)
public class SingletonCMCBeanImpl implements ConcurrencyBeanRemote {

	/**
	 * Quando o acesso CMC é utilizado, o container cria um lock para cada método do EJB Singleton. Porém é possivel especificar o tipo de lock explicitamente
	 * atraves da anotacao '@Lock'. A estrategia de lock especifica o tipo de acesso a este metodo. Quando o tipo LockType.WRITE é utilizado, somente uma thread
	 * pode acessar um metodo por vez desse EJB, as outras devem esperar o processamento ser encerrado. Quando o tipo LockType.READ é especificado, todas as threads
	 * podem acessar todos os metodos sem esperar pelo fim do processamento. Caso esta anotacao nao seja especificada, ou informada sem um parametro, o valor
	 * LockType.WRITE é utilizado por padrao. O LockType.WRITE faz com que todas as outras threads (de leitura ou escrita) esperem pelo fim da execucao desse metodo.
	 * <hr>
	 * A anotacao '@Lock' pode ser especificada tanto na classe como no metodo. Caso seja especificada nos dois pontos, o metodo levara precedencia.
	 */
	@Lock(LockType.READ)
	@Override
	public String method1() throws Exception {
		TimeUnit.SECONDS.sleep(5);
		return "Method1";
	}

	/**
	 * A Anotacao '@AccessTimeout' é utilizada para especificar o tempo que uma thread deve esperar antes de lancar uma excecao por que nao conseguiu o
	 * lock do metodo. É possivel especificar o tempo (value) e a unidade utilizada (a unidade padrao é em milisegundos).
	 * <hr>
	 * A anotacao '@AccessTimeout' pode ser especificada tanto na classe como no metodo. Caso seja especificada nos dois pontos, o metodo levara precedencia.
	 */
	@Lock(LockType.WRITE)
	@AccessTimeout(value = 10, unit = TimeUnit.SECONDS)
	@Override
	public String method2() throws Exception {
		TimeUnit.SECONDS.sleep(10);
		return "Method2";
	}
}
