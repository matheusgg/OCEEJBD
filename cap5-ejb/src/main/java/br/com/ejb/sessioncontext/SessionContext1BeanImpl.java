package br.com.ejb.sessioncontext;

import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.ejb.*;
import java.io.Serializable;
import java.security.Principal;

@Stateless
@Remote
@Log
public class SessionContext1BeanImpl implements SessionContext1BeanRemote, Serializable {

	private static final long serialVersionUID = 7007125589057611236L;

	/**
	 * A classe SessionContext permite utilizar os servicos disponibilizados pelo container de dentro do EJB corrente.
	 * Essa classe pode ser utilizada para recuperar informacoes sobre o contexto da invocacao atual. Como este objeto
	 * é gerenciado pelo container, para injeta-lo basta utilizar a anotacao @Resource. Um objeto SessionContext só pode
	 * ser injetado dentro de um EJB.
	 */
	@Resource
	private SessionContext sessionContext;

	@EJB
	private SessionContext2BeanRemote sessionContext2BeanRemote;

	@Override
	public String getMessage() {
		/*
		 * Nao é possivel passar um EJB como parametro para invocacao de método de outro EJB, ou seja,
		 * nao é possivel passar a referencia atual (this) como parametro para a chamada de outro EJB,
		 * isso resultará em uma excecao, pois é ilegal. Deste modo, o método getBusinessObject pode ser
		 * utilizado para retornar o proxy do EJB atual, esse proxy por sua vez, pode ser passado como
		 * parametro para invocacao de outro EJB. O método getBusinessObject deve receber a interface que
		 * é utilizada como view do EJB atual, ou seja, nao é possivel recuperar uma referencia de proxy
		 * de outro EJB com este método, apenas do EJB atual.
		 */
		SessionContext1BeanRemote sessionContext1BeanRemote = this.sessionContext.getBusinessObject(SessionContext1BeanRemote.class);
		log.info(this.sessionContext2BeanRemote.getName(sessionContext1BeanRemote));

		/*
		 * Isso resultará em uma excecao, pois é ilegal passar a referencia de um EJB para outro
		 * EJB remoto diretamente, apenas via EJB proxy. Porém, se o EJB SessionContext2BeanRemote estiver marcado como
		 * @Local, é perfeitamente possivel realizar essa chamada, uma vez que esses 2 EJBs estao na mesma
		 * VM.
		 */
		// this.sessionContext2BeanRemote.getName(this);

		/*
		 * Todos esses metodos resultarao em uma execao se forem invocados, uma vez que eram utilizados
		 * nas versoes anteriores da especificacao (EJB 2.1) e estao obsoletos.
		 */
		/*this.sessionContext.getEJBObject();
		this.sessionContext.getEJBLocalObject();
		this.sessionContext.getEJBHome();
		this.sessionContext.getEJBLocalHome();*/

		/*
		 * O método getMessageContext é utilizado para recuperar o contexto de mensagem caso este EJB esteja sendo
		 * utilizado com uma view de WebService.
		 */
		// this.sessionContext.getMessageContext();

		/*
		 * O método getUserTransaction é utilizado para recuperar a transacao atual caso este EJB esteja marcado
		 * com transacoes gerenciadas pelo Bean (BMT).
		 */
		// this.sessionContext.getUserTransaction();

		/*
		 * O método getInvokedBusinessInterface retorna o tipo da view que foi utilizada para expor este EJB.
		 */
		Class<?> viewInterface = this.sessionContext.getInvokedBusinessInterface();
		log.info(viewInterface.toString());

		/*
		 * O método getTimerService é utilizado para recuperar o TIimerService gerenciado pelo container.
		 */
		TimerService timerService = this.sessionContext.getTimerService();
		log.info(timerService.toString());

		/*
		 * O método getCallerPrincipal é utilizado para recuperar as informacoes do usuário que está invocando este EJB.
		 */
		Principal principal = this.sessionContext.getCallerPrincipal();
		log.info(principal.toString());

		/*
		 * O método isCallerInRole recebe uma String e pode ser utilizado para verificar se o cliente que está invocando este EJB
		 * possui a permissao necessária.
		 */
		// boolean admin = this.sessionContext.isCallerInRole("admin");
		// log.info("Is admin? " + admin);

		/*
		 * O método lookup é utilizado para recuperar outros EJBs atraves do EJB ENC dos mesmos. Um ponto importante é que com este método
		 * só é possível realizar o lookup de nomes que pertencam aos namespaces java:app, java:module, java:comp ou java:global.
		 */
		SessionContext2BeanRemote sessionContext2BeanRemote = (SessionContext2BeanRemote) this.sessionContext.lookup("java:global/cap5-ejb/SessionContext2BeanImpl!br.com.ejb.sessioncontext.SessionContext2BeanRemote");
		log.info(sessionContext2BeanRemote.toString());

		return "Test Message";
	}
}
