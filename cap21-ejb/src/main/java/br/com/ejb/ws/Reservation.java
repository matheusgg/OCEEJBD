package br.com.ejb.ws;

import javax.ejb.Remote;
import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Holder;
import java.math.BigDecimal;

import static javax.jws.WebParam.Mode.IN;
import static javax.jws.WebParam.Mode.OUT;
import static javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED;
import static javax.jws.soap.SOAPBinding.Style.RPC;
import static javax.jws.soap.SOAPBinding.Use.LITERAL;

/**
 * A anotacao @WebService serve para especificar um webservice endpoint. É possível definir EJBs, Servlets e até mesmo
 * classes simples como webservices.
 * <p>
 * Quando a anotacao @WebService é definida, todos os métodos públicos da classe sao expostos como métodos do webservice.
 * <p>
 * Caso a anotacao @WebService seja definida em uma interface, significa que o contrato do servico esta separado da implementacao,
 * neste caso, nao é permitido que a anotacao @WebService especifique atributos, uma vez que estes serao definidos da classe de
 * implementacao.
 * <p>
 * A anotacao @SoapBinding define o tipo de mensagem SOAP. Esta anotacao também influencia na definicao do WSDL.
 * <ul>
 * <li>
 * Caso seja especificado o valor RPC no atributo "style", o WSDL gerado nao possuira a secao de "types", ou seja, cada parametro será
 * mapeado como um elemento "part" no WSDL. Desta forma, o valor do atributo "parameterStyle" será ignorado.
 * </li>
 * <li>
 * Caso seja especificado o valor DOCUMENT no atributo "style", o WSDL gerado possuira a secao de "types" onde cada atributo sera
 * definido nessa secao.
 * </li>
 * <li>
 * O atributo "use" deve sempre possuir o valor LITERAL, uma vez que os componentes nao sao mais obrigados a suportarem outros valores.
 * </li>
 * <li>
 * Caso o valor BARE seja especificado no atributo "parameterStyle", apenas um parametro sera mapeado nas operacoes, ou seja, caso um
 * metodo receba mais do que um parametro, apenas o primeiro será considerado. Já o valor WRAPPED faz com que todos os parametros sejam mapeados.
 * </li>
 * </ul>
 */
@Remote
@WebService
@SOAPBinding(style = RPC, use = LITERAL, parameterStyle = WRAPPED)
public interface Reservation {

	/**
	 * A anotacao @WebMethod serve para especificar um método de um webservice. Desta forma, é possível modificar o nome do método
	 * do serviço, assim como definir o action que será utilizado como hint deste método.
	 * <p>
	 * O atributo action serve para definir o hint que corresponde a esta operação, isto é, quando o cabeçalho SOAPAction for informado,
	 * a implementação do JAX-WS verificara se este webservice tem alguma oparacao que possui o action igual ao valor especificado
	 * no cabecalho, caso positivo, o metodo que representa essa operacao sera invocado. Isso pode trazer grandes ganhos de performance,
	 * uma vez que a implementacao nao precisara analizar toda a mensagem SOAP a fim de determinar qual metodo devera ser invocado.
	 * <p>
	 * A anotacao @WebResult serve para especificar o que a implementacao do JAX-WS deve fazer com o valor de retorno desta operacao. Neste
	 * caso, o valor retornado por este metodo sera enviado como um SOAP Header para o cliente.
	 */
	@WebMethod(action = "makeReservationAction", operationName = "makeReservationOp")
	@WebResult(name = "clientId", header = true)
	int makeReservation(@WebParam(name = "key", header = true) final String key,
	                    @WebParam(name = "clientId", targetNamespace = "http://reservation.params.com", mode = IN) final int clientId,
	                    @WebParam(name = "amount") final BigDecimal amount,
	                    @WebParam(name = "response", mode = OUT) final Holder<Boolean> response);

	@WebMethod(action = "makeReservationReducedAcion", operationName = "makeReservationReducedOp")
	@WebResult(name = "response", targetNamespace = "http://reservation.params.com")
	boolean makeReservationReduced(@WebParam(name = "clientId") final int clientId,
	                               @WebParam(name = "amount") final BigDecimal amount);

	/**
	 * A anotacao @Oneway define uma operacao que possui apenas entradas. Ou seja, a resposta enviada para o cliente nao possuira corpo.
	 */
	@WebMethod(action = "cancelReservationsAcion", operationName = "cancelReservationsOp")
	@Oneway
	void cancelReservations(@WebParam(name = "clientId") final int clientId);
}
