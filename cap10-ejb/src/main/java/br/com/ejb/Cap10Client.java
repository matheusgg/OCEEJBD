package br.com.ejb;

import br.com.ejb.persistence.mapping.Address;
import br.com.ejb.persistence.mapping.Address.AddressInfo;
import br.com.ejb.stateless.GenericRemoteBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap10Client {

	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws Exception {
		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "admin123");
		env.put("jboss.naming.client.ejb.context", true);

		final Context ctx = new InitialContext(env);

		/*
		 * ======================================================================================================================================
		 * Table Generator
		 * ======================================================================================================================================
		 */
		/*final GenericRemoteBean<Employee, Integer> bean = (GenericRemoteBean<Employee, Integer>) ctx.lookup("cap10-ejb/EmployeeBeanImpl!br.com.ejb.stateless.GenericRemoteBean");
		Employee employee = new Employee(null, "Employee 1", new Date());

		employee = bean.save(employee);
		System.out.println(employee);

		employee = bean.findById(1);
		System.out.println(employee);

		System.out.println(bean.findAll());

		bean.delete(1);
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * Embedded ID - @IdClass
		 * ======================================================================================================================================
		 */
		/*final GenericRemoteBean<Company, CompanyPK> bean = (GenericRemoteBean<Company, CompanyPK>) ctx.lookup("cap10-ejb/CompanyBeanImpl!br.com.ejb.stateless.GenericRemoteBean");
		Company company = new Company("Company 1", 1, "Fictional Company Name");

		company = bean.save(company);
		System.out.println(company);

		company = bean.findById(new CompanyPK("Company 1", 1));
		System.out.println(company);

		System.out.println(bean.findAll());

		bean.delete(new CompanyPK("Company 1", 1));
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * Embedded ID - @EmbeddedId e @Embeddable
		 * ======================================================================================================================================
		 */
		/*final GenericRemoteBean<Partner, PartnerEmbeddedPK> bean = (GenericRemoteBean<Partner, PartnerEmbeddedPK>) ctx.lookup("cap10-ejb/PartnerBeanImpl!br.com.ejb.stateless.GenericRemoteBean");
		final PartnerEmbeddedPK pk = new PartnerEmbeddedPK("Partner 1", 1);
		Partner partner = new Partner(pk, new Date());

		partner = bean.save(partner);
		System.out.println(partner);

		partner = bean.findById(pk);
		System.out.println(partner);

		System.out.println(bean.findAll());

		bean.delete(pk);
		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * Embedded Objects - @Embedded e @Embeddable
		 * ======================================================================================================================================
		 */
		final GenericRemoteBean<Address, Integer> bean = (GenericRemoteBean<Address, Integer>) ctx.lookup("cap10-ejb/AddressBeanImpl!br.com.ejb.stateless.GenericRemoteBean");
		final AddressInfo info = new AddressInfo("São Paulo", "SP");
		Address address = new Address(null, "Street", info);

		address = bean.save(address);
		System.out.println(address);

		address = bean.findById(1);
		System.out.println(address);

		System.out.println(bean.findAll());

		/*bean.delete(1);
		System.out.println(bean.findAll());*/
	}

}
