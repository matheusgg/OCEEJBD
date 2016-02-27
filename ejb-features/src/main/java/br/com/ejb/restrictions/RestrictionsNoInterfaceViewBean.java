package br.com.ejb.restrictions;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class RestrictionsNoInterfaceViewBean {

	public RestrictionsNoInterfaceViewBean testPassingThis(final RestrictionsBeanImpl bean) {
		return this;
	}

}
