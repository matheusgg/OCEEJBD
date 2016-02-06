package br.com.ejb.inconsistency;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class InconsistencyBeanImpl implements InconsistencyBeanRemote {

	private String name;

	@Override
	public String sayHello(String name) {
		if (this.name == null) {
			this.name = name;
			return "Hi " + this.name;
		}

		String msg = "Your name is " + this.name;
		this.name = null;
		return msg;
	}

}
