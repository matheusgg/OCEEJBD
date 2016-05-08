package br.com.ejb.lifecycle;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Um EJB Stateful possui 3 fases em seu ciclo de vida: Does Not Exist, Method-Ready e Passivated. A grande diferenca entre EJB Stateless e
 * Stateful é que estes ultimos nao sao colocados em um pool de beans como acontece com os EJBs Stateless. Uma vez que o mesmo EJB atendera
 * sempre o mesmo cliente durante toda a sessao, o container nao cria um pool de beans Stateful. Desta forma, o ciclo de vida de EJBs Stateful
 * se diferencia do ciclo de vida de EJBs Stateless na fase Method-Ready, ja que no caso dos EJBs Stateless existe a fase Method-Ready Pool.
 * <ul>
 * <li>
 * Quando um EJB esta na fase Does Not Exist, nenhuma instancia esta em memoria e ele ainda nao esta pronto para receber requisicoes.
 * </li>
 * <li>
 * Quando um cliente invoca um método de negocio pela primeira vez de um EJB Stateful, o container constroi uma instancia desse EJB
 * atraves da chamada a Class.newInstance(), inicializa todos os pontos de injecao e invoca qualquer metodo marcado com a anotacao
 * '@PostContruct'. Depois disso, o container coloca o EJB na fase Method-Ready e o mesmo está pronto para atender as requisicoes
 * do cliente que o invocou.
 * </li>
 * <li>
 * Quando um EJB esta na fase Method-Ready, ele pode tanto voltar para a fase Does Not Exist ou entrar na fase Passivated. Quando um EJB
 * atinge o timeout, ele volta para a fase Does Not Exist, onde o container libera o objeto para o GC (o timeout é definido de acordo com
 * a implementacao do container EJB). Outro fato que faz com que um EJB saia da fase Method-Ready e volte para Does Not Exist é quando algum
 * metodo de negocio lanca uma system exception (ou seja, qualquer uncheched exception que nao esteja anotada com '@ApplicationException').
 * Quando um metodo de negocio lanca um system exception, o container libera o objeto para o GC e qualquer metodo anotado com '@PreDestroy'
 * nao sera invocado. Por fim, quando um metodo de negocio marcado com '@Remove' é invocado, apos a sua execucao o container destroy a
 * instancia do EJB e o coloca novamente na fase Does Not Exist.
 * </li>
 * <li>
 * Quando um EJB esta na fase Method-Ready, o container pode remover o objeto da memoria para liberar espaco. Isso acontece de acordo
 * com o algoritmo implementado pelo container. Desta forma, o EJB entra na fase Passivated. Quando um EJB esta prestes a entrar na fase
 * Passivated, o container serializa o objeto, assim como todos os atributos do mesmo (caso algum atributo inicializado nao seja serializavel, uma execao
 * sera lancada). Depois disso, o container invoca o metodo anotado com '@PrePassivate'. Deste modo, é possivel liberar qualquer
 * recurso alocado pelo EJB.
 * </li>
 * <li>
 * Um EJB no estado Passivated pode passar diretamente para a fase Does Not Exist caso o timeout definido seja atingido. Caso isso ocorra,
 * o container nao é obrigado a invocar os metodos anotados com '@PreDestroy'.
 * </li>
 * <li>
 * Quando um metodo de negocio de um EJB que esta na fase Passivated é invocado, o container desserializa o objeto salvo e o carrega novamente
 * na memoria. Depois disso, qualquer método marcado com '@PostActivate' é chamado para inicializar o bean. É somente apos a invocacao do
 * '@PostActivate' que o EJB passa novamente para a fase Method-Ready e o metodo solicitado é chamado.
 * </li>
 * </ul
 */
@Log
@Stateful
public class LifecycleBeanImpl implements LifecycleBeanRemote {

	private String clientID;

	/**
	 * Uma excecao sera lancada no momento que este EJB for passivado, pois a classe Teste nao é serializavel.
	 */
	// private Teste teste;
	@PostConstruct
	void init() {
		// this.teste = new Teste();
		this.clientID = "Client " + (int) (Math.random() * 1000);
		log.info(this.clientID + " - Initializing the bean instance...");
	}

	@PreDestroy
	void destroy() {
		log.info(this.clientID + " - Destroying the bean instance...");
	}

	/**
	 * Quando o container esta prestes a serializar a instancia do EJB para remove-la da memoria, ele chama o metodo anotado com '@PrePassivate'.
	 * A classe de implementacao do EJB nao precisa implementar a interface Serializable, porem os atributos desta classe precisam. Caso um EJB
	 * Stateful possui algum atributo inicializado nao serializavel, uma excecao sera lancada no momento que este EJB for passivado. Porem, se
	 * o atributo nao serializado nao esteja inicializado, nenhuma excecao sera lancada e o processo de serializacao continuara normalmente.
	 */
	@PrePassivate
	void prePassivate() {
		log.info(this.clientID + " - Bean is being passivated...");
	}

	/**
	 * Método invocado quando o container desserializa o objeto e o traz novamente para a memoria e para a fase Method-Ready.
	 * Assim como os metodos anotados com '@PostConstruct', '@PreDestroy' e '@PrePassivate', o método anotado com '@PostActivate'
	 * pode possuir qualquer visibilidade, deve ser void e nao pode ser final ou estatico.
	 */
	@PostActivate
	void postActivate() {
		log.info(this.clientID + " - Bean is being activated...");
	}

	@Override
	public void firstMethod(String id) {
		this.clientID = id;
	}

	@Override
	public String doSomeWork() {
		return this.clientID + " - Some work is done!";
	}

	@Override
	public String doAnotherWork() {
		return this.clientID + " - Some another work!";
	}

	/**
	 * Quando um metodo anotado com '@Remove' é invocado, o container libera o objeto para o GC e envia o EJB novamente para a fase Does Not Exist.
	 * O atributo retainIfException=true faz com que o bean nao seja removido da fase Method-Ready caso alguma excecao seja lancada. Por padrao,
	 * o valor desse atributo é false.
	 */
	@Remove(retainIfException = true)
	@Override
	public void removeBean1(boolean throwException) {
		if (throwException) {
			throw new IllegalArgumentException(this.clientID + " - Test retainIfException = true");
		}
		log.info(this + " - This bean instance will be removed!");
	}

	/**
	 * Apos a invocacao do metodo marcado com '@Remove', o container invoca o metodo marcado com '@PreDestroy' antes de liberar a instancia para o GC.
	 */
	@Remove
	@Override
	public void removeBean2() {
		log.info(this.clientID + " - This bean instance will be removed!");
	}

	/**
	 * Qualquer system exception lancada por um metodo de negocio fara com que o EJB Stateful saia da fase Method-Ready e entre novamente na
	 * fase Does Not Exist. Fazendo com que o container libere esse objeto para o GC.
	 *
	 * @throws RuntimeException
	 */
	@Override
	public void throwSystemException() throws RuntimeException {
		throw new RuntimeException(this.clientID + " - Test throw system exception.");
	}

	/**
	 * Qualquer application exception lancada por um metodo de negocio fara com que o EJB Stateful saia da fase Method-Ready e entre novamente na
	 * fase Does Not Exist. Fazendo com que o container libere esse objeto para o GC.
	 *
	 * @throws Exception
	 */
	@Override
	public void throwApplicationException() throws Exception {
		throw new IllegalArgumentException(this.clientID + " - Test throw application exception.");
	}

}

/*
class Teste {

}*/
