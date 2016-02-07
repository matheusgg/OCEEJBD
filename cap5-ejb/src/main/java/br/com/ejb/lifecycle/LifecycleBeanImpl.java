package br.com.ejb.lifecycle;

import br.com.ejb.model.SumModel;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * O ciclo de vida de um EJB Stateless é muito simples e é composto de apenas 2 fases: "Does Not Exist" e "Method-Ready Pool".
 * Primeiramente o bean nao existente entrará na fase "Does Not Exist", quando o container criar uma instancia deste bean, o mesmo
 * passará para a fase "Method-Ready Pool", durante a transicao, o container instancia o EJB atraves da chamada ao método Class.newInstance()
 * (O EJB precisa possuir um construtor padrao sem argumentos para que o container possa criar a instancia do bean), inicializa o bean
 * injetando todos os objetos necessários, depois disso, o container invoca o método marcado com a anotacao @PostContruct e adiciona este
 * bean no pool de beans. Neste ponto, o EJB está pronto para atender as requisicoes e ter seus métodos invocados. O container pode, em algum
 * momento, destruir a instancia de um EJB, desta forma, ele invoca o método marcado com a anotacao @PreDestroy e libera a instancia para o GC.
 * Neste momento, o EJB está novamente na fase "Does Not Exist".
 * <hr>
 * Uma instancia só é retirada do pool para ser utilizada quando um método de negocio é invocado, ou seja, o lookup de um EJB nao faz com que
 * o mesmo seja retirado do pool para ser utilizado. Quando um método de negocio está sendo executando, o EJB fica indisponivel para ser
 * utilizado por outros clientes.
 */
@Stateless
@Remote
@Local(LifecycleBeanLocal.class)
@Log
public class LifecycleBeanImpl implements LifecycleBeanRemote {

	private SumModel sumModel;

	/**
	 * O método anotado com @PostConstruct deve ser void e nao deve receber parametros.
	 */
	@PostConstruct
	protected void init() {
		log.info("Initializing " + this.getClass().getSimpleName());
	}

	@Override
	public int sum(SumModel sumModel) {
		if (sumModel != null) {
			this.sumModel = sumModel;
		}
		return this.sumModel.getNum1() + this.sumModel.getNum2();
	}

	/**
	 * O método anotado com @PreDestroy deve ser void e nao deve receber parametros.
	 */
	@PreDestroy
	protected void destroy() {
		log.info("Destroying " + this.getClass().getSimpleName());
	}
}
