package br.com.ejb.stateful;

import javax.ejb.Stateful;

/**
 * Um EJB Stateful se diferencia dos EJB Stateless pelo fato de que, conceitualmente, ele é um agente do cliente do lado do
 * servidor. Quando um EJB Stateful é associado a um cliente, este objeto sempre atendera as requisicoes deste mesmo cliente
 * durante toda a sessao. Desta forma, um EJB Stateful pode possuir um "conversational state", pois como o mesmo objeto atendera
 * sempre o mesmo cliente, nao ha problemas em este objeto possuir um estado. Com essa abordagem, qualquer invocacao de metodo
 * de negocio deste EJB pode alterar o estado do objeto para as demais invocacoes.
 */
@Stateful
public class SimpleStatefulBeanImpl implements SimpleStatefulBeanRemote {
}
