package br.com.ejb.mdb.activationconfigs;

import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * A especificacao JMS possui alguns papeis bem definidos para tratamento de mensagens JMS:
 * <ul>
 * <li>JMS Provider: O provedor JMS responsavel por realizar o roteamento e o envio das mensagens JMS;</li>
 * <li>JMS Client: Um cliente que pode tanto consumir quanto produzir mensagens JMS;</li>
 * <li>JMS Producer: Um cliente que produz mensagens JMS (ex.: um SessionBean);</li>
 * <li>JMS Consumer: Um cliente que consome mensagens JMS (ex.: um MDB);</li>
 * <li>Destination: O tipo de destino das mensagens JMS (Topico ou Fila);</li>
 * </ul>
 * <hr>
 * As filas JMS (queues) seguem o modelo point-to-point (p2p), ou seja, quando uma mensagem é enviada, ela é processada por apenas um consumer,
 * ou seja, quando a mensagem termina de ser processada, ele é removida da fila.
 * Já os tópicos JMS (topics) seguem o modelo publish-and-subscribe, ou seja, os consumers devem se associar a um tópico e quando uma
 * mensagem é enviada para este topico especifico, cada consumer recebe uma copia da mensagem. A grande diferenca entre filas e topicos é que
 * enquanto em uma fila apenas um consumer processa a mensagem, em um topico varios consumers podem processar a mesma mensagem enviada para aquele
 * topico.
 * <hr>
 * Qualquer tipo de EJB pode realizar o papel de um JMS Producer, ou seja, tanto EJBs Stateless, Stateful ou Singletons podem
 * produzir mensagens JMS.
 * <hr>
 * A especificacao de EJBs disponibiliza um tipo especifico de EJB que deve ser utilizado para consumir mensagens JMS.
 * Os Message Driven Beans sao EJBs Stateless gerenciados pelo container EJB que sao utilizados como listeners de destinos de
 * mensagens, sejam elas mensagens JMS ou qualquer outro tipo suportado pela JCA.
 * <hr>
 * MDBs sao EJBs responsaveis por consumir mensagens de um determinado destino (geralmente uma fila ou um tópico JMS).
 * A especificacao JMS está intimamente ligada com a especificacao de EJBs. Uma vez que é um pre-requisito para os servidores
 * de aplicacao possuirem suporte a provedores JMS, é possivel integrar essas duas especificacoes consumindo ou produzindo mensagens JMS
 * atraves de EJBs.
 * <hr>
 * Por causa da natureza assincrona da especificacao JMS, os MDBs sao um tipo de EJB diferente dos SessionBeans, pois os MDBs
 * nao sao acessados diretamente pelos clientes. O acesso a esse tipo de EJB é feito pelo container EJB, isto é, quando uma mensagem
 * é disponibilizada, o container EJB é notificado e imediatamente invoca um MDB (caso esteja disponivel) para consumir essa mensagem.
 * <hr>
 * Para definir um MDB basta anotar o bean com '@MessageDriven'. Como um MDB nao é acessado diretamente pelos clientes, ele nao expoe nenhuma
 * view.
 * <hr>
 * Para configurar os destinos de onde o MDB recupera as mensagens, basta utilizar o atributo activationConfig passando diversas
 * '@ActivationConfigProperty', que nada mais é do que uma anotacao que possui propriedades no formato chave=valor para configurar os destinos
 * JMS de acordo com o JMS Provider. Essa abordagem foi adotada para disponibilizar uma maior flexibilidade, pois cada JMS provider
 * pode possuir parametros de configuracoes diferentes.
 * <hr>
 * A versao 3 da especificacao de EJBs definiu algumas propriedades de configuracao padrao na definicao dos MDBs:
 * <ul>
 * <li>
 * acknowledgeMode: É o tipo de resposta de recebimento enviada pelo EJB container para o JMS Provider. Pode ser Auto_acknowledge (padrao) ou Dups_ok_acknowledge.
 * Quando Auto_acknowledge é especificado e o container EJB recebe uma mensagem do JMS provider, imediatamente ele envia uma resposta para o JMS provider
 * informando que a mensagem foi recebida.
 * Quando Dups_ok_acknowledge é especificado e o container EJB recebe uma mensagem do JMS Provider, o container nao envia a resposta de recebimento
 * imediatamente, ao inves disso o container pode enviar a resposta em um momento futuro, mas nao necessariamente assim que ele recebe a mensagem. Desta forma,
 * o JMS provider pode reenviar a mensagem achando que o container nao a recebeu.
 * Este atributo é utilizado apenas quando o tipo de transacao é gerenciado pelo bean (BMT), pois caso a transacao seja gerenciada pelo container (CMT),
 * a resposta sera enviada se a transacao for executada com sucesso, caso contrario, a resposta nao sera enviada, desconsiderando assim, o valor desse atributo.
 * </li>
 * <li>
 * messageSelector: Propriedade utilizada para filtar as mensagens que este MDB recebera. Utiliza logica booleana para filtrar mensagens que possuem headers
 * ou propriedades com o valor especificado nesta configuracao.
 * </li>
 * <li>
 * destinationType: Tipo do destino de mensagens JMS que serao processadas por este MDB (javax.jms.Queue ou javax.jms.Topic).
 * </li>
 * <li>
 * subscriptionDurability: Propriedade utilizada quando o MDB consome mensagens de um topico. Esta configuracao informa se o MDB deve ser duravel ou nao.
 * Quando o valor Durable é especificado e o MDB fica indisponivel, as mensagens enviadas neste periodo de tempo nao serao perdidas, deste modo,
 * quando o MDB voltar a operar, ele recebera todas as mensagens enviadas durante o intervalo de indisponibilidade.
 * Quando o valor NonDurable (default) é especificado e o MDB fica indisponivel, todas as mensagens enviadas durante esse intervalo de indisponibilidade serao perdidas.
 * </li>
 * <li>
 * destinationLookup ou destination: Nome JNDI do recurso de destino de mensagens que sao lidas, ou seja, nome JNDI da fila ou topico de mensagens JMS que este MDB recebera
 * notificacoes.
 * </li>
 * </ul>
 */
@Log
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto_acknowledge"),
		@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "Seletor='1'"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/queue/simple"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/simple")})
public class ActivationConfigsMDB implements MessageListener {

	@Override
	public void onMessage(final Message message) {
		try {
			log.info(((TextMessage) message).getText());
		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "onMessage", e);
		}
	}
}
