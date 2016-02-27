package br.com.ejb.restrictions;

import lombok.extern.java.Log;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Log
@Stateful
public class RestrictionsBeanImpl implements RestrictionsRemoteBean {

	private static String someText;

	@EJB
	private RestrictionsNoInterfaceViewBean noInterfaceViewBean;

	@Override
	public void testRestrictions() throws InterruptedException, IOException, NamingException {
		/*
		 * ======================================================================================================================================
		 * É POSSIVEL MAS NAO DEVE SER UTILIZADO
		 * ======================================================================================================================================
		 */

		/*
		 * EJBs nao devem alterar o contexto que não pertece a ele mesmo, ou seja, não deve ser feito bind ou rebind de propriedades.
		 */
		final Context ctx = new InitialContext();

		ctx.bind("prop", "Teste");
		log.info(ctx.lookup("prop").toString());

		ctx.rebind("prop", "Teste Rebind");
		log.info(ctx.lookup("prop").toString());

		/*
		 * Um EJB nao deve utilizar read/write static fields, pois esta operacao pode nao ser suportada por
		 * todos os containers EJB.
		 */
		someText = "Algum Texto";
		log.info(someText);

		/*
		 * Um EJB nao deve utilizar sincronizacao primitiva de thread, pois como é o container EJB que gerencia o
		 * acesso concorrente, a sincronizacao manual pode conflitar com esse gerenciamento.
		 */
		synchronized (someText) {
			log.info("Lock obtido!");
			TimeUnit.SECONDS.sleep(1);
			someText.notifyAll();
		}

		/*
		 * Um EJB nao deve utilizar as classes do pacote java.io, pois alguns containers podem nao suportar essas operacoes. EJBs deve utilizar
		 * APIs gerenciadoras de recursos externos, como por exemplo, JDBC.
		 */
		final File simpleFile = new File("/Users/Matheus/Documents/Repositories/OCEEJBD/ejb-features/someFolder/simpleFile.txt");
		final PrintWriter writer = new PrintWriter(new FileOutputStream(simpleFile));
		writer.println(someText);
		writer.close();

		/*
		 * EJBs nao devem ler nem escrever em um FileDescriptor, uma vez que esta é uma tarefa gerenciada pelo container.
		 */
		final FileInputStream is = new FileInputStream(simpleFile);
		log.info(String.valueOf(is.getFD().valid()));
		is.close();

		/*
		 * EJBs nao devem utilizar sockets para aceitar conexoes de entrada, uma vez que isto conflita com a natureza dos beans, pois
		 * EJBs devem ser utilizados para servir EJBs clients e nao se tornar servidores que aceitam conexoes.
		 */
		/*final ServerSocket serverSocket = new ServerSocket(8181);
		final Socket socket = serverSocket.accept();
		final InputStream inputStream = socket.getInputStream();
		final Scanner scan = new Scanner(inputStream);
		log.info(scan.nextLine());
		serverSocket.close();*/

		/*
		 * EJBs nao devem utilizar a API de reflection para recuperar informacoes de membros que nao estao acessiveis para este EJB atraves
		 * de maneiras permitidas pela JVM;
		 *
		 * EJBs nao devem tentar carregar bibliotecas nativas;
		 *
		 * EJBs nao devem tentar obter acesso a pacotes ou classes que a JVM restrinja;
		 *
		 * EJBs nao devem tentar definir classes em pacotes.
		 */
		final SimpleClass simpleClass = new SimpleClass();
		final Class<? extends SimpleClass> clazz = simpleClass.getClass();
		final Method[] declaredMethods = clazz.getDeclaredMethods();
		System.out.println(Arrays.asList(declaredMethods));

		/*
		 * EJBs nao devem iniciar, resumir, parar ou suspender uma Thread, pois essas sao tarefas do container EJB.
		 */
		final ExecutorService executorService = Executors.newFixedThreadPool(5);
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				log.info("Teste " + Thread.currentThread().getName());
			}
		});
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				log.info("Teste " + Thread.currentThread().getName());
			}
		});
		executorService.shutdown();

		/*
		 * EJBs nao devem passar e nem retornar a referencia de si mesmo (this) para outros metodos.
		 */
		final RestrictionsNoInterfaceViewBean bean = this.noInterfaceViewBean.testPassingThis(this);
		log.info(bean.toString());

		/*
		 * ======================================================================================================================================
		 * NÃO É POSSÍVEL E NAO DEVE SER UTILIZADO
		 * ======================================================================================================================================
		 */
		/*
		 * Um EJB nao deve utilizar as classes do pacote java.awt, uma vez que servidores de aplicacao nao permitem interacao direta pelo teclado
		 * ou exibicao de informacoes em tela.
		 */
		// JOptionPane.showMessageDialog(new JPanel(), "Mensagem de Teste");

		/*
		 * Um EJB nao deve recuperar o ClassLoader, definir o SecurityManager, o Stream de entrada ou o Stream de saida. Uma vez que essas
		 * sao operacoes reservadas para o container EJB, permitir que os proprios beans tenham acesso a essas funcionalidades diminuiria
		 * a capacidade, a seguranca e a autonomia do container.
		 *
		 * EJBs nao podem acessar ou modificar objetos de seguranca, como por exemplo, SecurityManager, Policy, Provider, etc.
		 */
		/*final ClassLoader classLoader = this.getClass().getClassLoader();
		final SecurityManager securityManager = new SecurityManager();
		System.setSecurityManager(securityManager);*/
	}
}
