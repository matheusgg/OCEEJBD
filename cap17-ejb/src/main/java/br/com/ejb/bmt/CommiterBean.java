package br.com.ejb.bmt;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.UserTransaction;

@Stateless
public class CommiterBean {

	@Resource(lookup = "java:comp/UserTransaction")
	private UserTransaction userTransaction;

	public void commit() throws Exception {
		this.userTransaction.commit();
	}

}
