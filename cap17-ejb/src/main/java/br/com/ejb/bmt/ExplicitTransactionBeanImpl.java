package br.com.ejb.bmt;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Para utilizar o gerenciamento explicito de transacoes em um EJB é preciso aplicar a anotacao @TransactionManagement com tipo TransactionManagementType.BEAN.
 * <p>
 * Quando a anotacao @TransactionManagement nao é definida ou é especificada sem um tipo de gerenciamento de transacao, o valor TransactionManagementType.CONTAINER
 * é assumido por padrao.
 * <p>
 * Os EJBs possuem suporte a especificacao JTA (Java Transaction API), desta forma, quando o gerenciamento explicito de transacoes é utilizado, é preciso interagir
 * diretamente com as classes da api JTA.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ExplicitTransactionBeanImpl implements ExplicitTransactionRemoteBean {

	private static final long serialVersionUID = -9142279509072237469L;

	@PersistenceContext
	private EntityManager em;

	@Resource(lookup = "java:comp/EJBContext")
	private SessionContext sessionContext;

	/**
	 * A classe TransactionManager da api JTA é responsavel pelas operacoes de controle explicito de transacoes.
	 * A injecao de uma instancia deste tipo varia de acordo com cada servidor de aplicacao.
	 */
	@Resource(lookup = "java:/TransactionManager")
	private TransactionManager txManager;

	/**
	 * A especificacao EJB define um nome JNDI para recuperar o objeto UserTransaction da api JTA para controlar
	 * explicitamente as transacoes: java:comp/UserTransaction.
	 * <p>
	 * O objeto UserTransaction pode ser recuperado tambem atraves do metodo SessionContext.getUserTransaction(), porem
	 * se o EJB estiver marcado com o tipo de gerenciamento de transacao TransactionManagementType.CONTAINER, uma
	 * java.lang.IllegalStateException sera lancada.
	 */
	@Resource(lookup = "java:comp/UserTransaction")
	private UserTransaction userTransaction;

	/***********************************************************************************************************************************************
	 * TransactionManager
	 * Caso o tipo de gerenciamento de transacao seja TransactionManagementType.CONTAINER, uma javax.transaction.NotSupportedException será lancada
	 * se ocorrer alguma tentativa de criacao de uma nova transacao explicitamente.
	 ***********************************************************************************************************************************************/
	@Override
	public Object executeWithTransactionManager(final Object value) throws Exception {
		try {
			this.txManager.begin();
			this.em.persist(value);
			this.txManager.commit();
		} catch (final Exception e) {
			this.txManager.rollback();
			e.printStackTrace();
		}
		return value;
	}

	/***********************************************************************************************************************************************
	 * UserTransaction
	 * Caso o tipo de gerenciamento de transacao seja TransactionManagementType.CONTAINER, uma javax.transaction.NotSupportedException será lancada
	 * se ocorrer alguma tentativa de criacao de uma nova transacao explicitamente.
	 ***********************************************************************************************************************************************/
	@Override
	public Object executeWithUserTransaction(final Object value) throws Exception {
		try {
			this.userTransaction.begin();
			this.em.persist(value);
			this.userTransaction.commit();
		} catch (final Exception e) {
			this.userTransaction.rollback();
			e.printStackTrace();
		}
		return value;
	}

	/***********************************************************************************************************************************************
	 * Transaction Propagation with BMT
	 * É possivel propagar a transacao gerenciada explicitamente, neste caso, a transacao se inicia em executeWithTransactionPropagation() e é
	 * encerrada dentro do método commit() do EJB CommiterBean.
	 ***********************************************************************************************************************************************/
	@Override
	public Object executeWithTransactionPropagation(final Object value) throws Exception {
		final UserTransaction tx = this.sessionContext.getUserTransaction();
		try {
			tx.begin();
			this.em.persist(value);
			((CommiterBean) this.sessionContext.lookup("java:global/cap17-ejb/CommiterBean")).commit();
		} catch (final Exception e) {
			tx.setRollbackOnly();
			e.printStackTrace();
		}
		return value;
	}
}
