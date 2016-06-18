package br.com.ejb.cmt;

import javax.ejb.*;

/**
 * Existem 6 tipos de transacoes EJB: NOT_SUPPORTED, SUPPORTS, REQUIRED, REQUIRES_NEW, MANDATORY e NEVER.
 * <p>
 * A anotacao @TransactionAttribute serve para definir o tipo de transacao que sera utilizada durante a chamada aos métodos de um EJB.
 * <p>
 * Quando esta anotacao nao é definida ou é nao é especificado o atributo de tipo de transacao, o valor TransactionAttributeType.REQUIRED
 * é assumido por padrao.
 * <p>
 * É possível especificar esta anotacao tanto a nivel de classe quanto a nível de método. Caso seja especificado nos dois locais, a anotacao
 * definida no método será utilizada.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
//@TransactionManagement(TransactionManagementType.CONTAINER) // Default
public class TransactionAttributesBeanImpl implements TransactionAttributesRemoteBean {

	private static final long serialVersionUID = 2916030962878517333L;

	@EJB
	private NotSupportedBean notSupportedBean;

	@EJB
	private SupportsBean supportsBean;

	@EJB
	private RequiredBean requiredBean;

	@EJB
	private RequiresNewBean requiresNewBean;

	@EJB
	private MandatoryBean mandatoryBean;

	@EJB
	private NeverBean neverBean;

	/**********************************************************************************
	 * NOT_SUPPORTED
	 **********************************************************************************/
	@Override
	public Object executeNotSupportedWithinTransaction(final Object value) {
		return this.notSupportedBean.save(value);
	}

	/**********************************************************************************
	 * SUPPORTS
	 **********************************************************************************/
	@Override
	public Object executeSupportsWithinTransaction(final Object value) {
		return this.supportsBean.save(value);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Object executeSupportsWithoutTransaction(final Object value) {
		return this.supportsBean.save(value);
	}

	/**********************************************************************************
	 * REQUIRED
	 **********************************************************************************/
	@Override
	public Object executeRequiredWithinTransaction(final Object value) {
		return this.requiredBean.save(value);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Object executeRequiredWithoutTransaction(final Object value) {
		return this.requiredBean.save(value);
	}

	/**********************************************************************************
	 * REQUIRES_NEW
	 **********************************************************************************/
	@Override
	public Object executeRequiresNewWithinTransaction(final Object value) {
		return this.requiresNewBean.save(value);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Object executeRequiresNewWithoutTransaction(final Object value) {
		return this.requiresNewBean.save(value);
	}

	/**********************************************************************************
	 * MANDATORY
	 **********************************************************************************/
	@Override
	public Object executeMandatoryWithinTransaction(final Object value) {
		return this.mandatoryBean.save(value);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Object executeMandatoryWithoutTransaction(final Object value) {
		return this.mandatoryBean.save(value);
	}

	/**********************************************************************************
	 * NEVER
	 **********************************************************************************/
	@Override
	public Object executeNeverWithinTransaction(final Object value) {
		return this.neverBean.save(value);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Object executeNeverWithoutTransaction(final Object value) {
		return this.neverBean.save(value);
	}

}
