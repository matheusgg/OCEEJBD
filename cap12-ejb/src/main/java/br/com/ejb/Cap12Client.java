package br.com.ejb;

import br.com.ejb.beans.GenericRemoteBean;
import br.com.ejb.model.CountryHouse;
import br.com.ejb.model.House;
import br.com.ejb.model.LakeHouse;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Cap12Client {

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
		 * SINGLE_TABLE
		 * ======================================================================================================================================
		 */
		/*final GenericRemoteBean<Person, Integer> bean = (GenericRemoteBean<Person, Integer>) ctx.lookup("cap12-ejb/PersonBeanImpl!br.com.ejb.beans.GenericRemoteBean");

		final Person person = new Person(null, "Matheus", "Goes");
		bean.save(person);

		final Customer customer = new Customer("Street", "São Paulo", "SP", "00000000");
		customer.setFirstName("Matheus");
		customer.setLastName("Goes");
		bean.save(customer);

		final Employee employee = new Employee(27346);
		employee.setFirstName("Matheus");
		employee.setLastName("Goes");
		bean.save(employee);

		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * TABLE_PER_CLASS
		 * ======================================================================================================================================
		 */
		/*final GenericRemoteBean<Animal, Integer> bean = (GenericRemoteBean<Animal, Integer>) ctx.lookup("cap12-ejb/AnimalBeanImpl!br.com.ejb.beans.GenericRemoteBean");

		*//*final Animal animal = new Animal(null, "Animal", "Animal");
		bean.save(animal);*//*

		final Dog dog = new Dog("Puddle", 5.5);
		dog.setName("Rex");
		dog.setSpecie("Dog");
		bean.save(dog);

		final Snake snake = new Snake(false);
		snake.setSpecie("Snake");
		snake.setName("Dangerous");
		bean.save(snake);

		System.out.println(bean.findAll());*/

		/*
		 * ======================================================================================================================================
		 * JOINED
		 * ======================================================================================================================================
		 */
		final GenericRemoteBean<House, Integer> bean = (GenericRemoteBean<House, Integer>) ctx.lookup("cap12-ejb/HouseBeanImpl!br.com.ejb.beans.GenericRemoteBean");

//		final House house = new House(null, "Some Address");
//		bean.save(house);

		final LakeHouse lakeHouse = new LakeHouse("Some Lake");
		lakeHouse.setAddress("Some Lake Address");
		bean.save(lakeHouse);

		final CountryHouse countryHouse = new CountryHouse("Some Country");
		countryHouse.setAddress("Some Country Address");
		bean.save(countryHouse);

//		System.out.println(bean.findAll());
	}

}
