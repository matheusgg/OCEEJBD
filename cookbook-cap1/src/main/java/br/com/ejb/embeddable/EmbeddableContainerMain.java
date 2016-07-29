package br.com.ejb.embeddable;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static javax.ejb.embeddable.EJBContainer.APP_NAME;
import static javax.ejb.embeddable.EJBContainer.MODULES;

/**
 * O EJB Embedded Container é uma versao simplificada do container EJB criado na versao 3.1 da especificacao.
 * Assim como na versao Lite do EJB 3.1, o EJB Embedded Container suporta menos recursos ainda.
 * <ul>
 * <li>Local Session Beans;</li>
 * <li>Transactions;</li>
 * <li>Security;</li>
 * <li>Interceptors;</li>
 * <li>Deployment Descriptor (ejb-jar.xml).</li>
 * </ul>
 */
public class EmbeddableContainerMain {

	public static void main(final String[] args) throws Exception {
		final Map<String, Object> properties = new HashMap<>();
		properties.put(MODULES, new File("/Users/Matheus/Documents/Repositories/OCEEJBD/cookbook-cap1/target/classes"));
		properties.put(APP_NAME, "cookbook-cap1");

		final EJBContainer ejbContainer = EJBContainer.createEJBContainer(properties);
		final Context context = ejbContainer.getContext();

		final EchoBeanImpl echoBean = (EchoBeanImpl) context.lookup("java:global/cookbook-cap1/classes/EchoBeanImpl");
		System.out.println(echoBean.echo("Funfo!!!"));

		ejbContainer.close();
	}

}
