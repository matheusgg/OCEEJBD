package br.com.ejb.ws.client;

import br.com.ejb.ws.Reservation;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

import static br.com.ejb.ws.client.ReservationClient.NAMESPACE;
import static br.com.ejb.ws.client.ReservationClient.WSDL;

/**
 * A classe anotada com @WebServiceClient deve estender a classe javax.xml.ws.Service.
 */
@WebServiceClient(name = "ReservationService", targetNamespace = NAMESPACE, wsdlLocation = WSDL)
public class ReservationClient extends Service {

	static final String NAMESPACE = "http://reservation.com";
	static final String WSDL = "http://localhost:8080/cap21-ejb/ReservationService/ReservationBean?wsdl";

	/**
	 * Este construtor sera chamado para a criacao desse client recebendo os valores dos atributos wsdlLocation e name
	 * especificados na anotacao @WebServiceClient.
	 */
	public ReservationClient(final URL wsdlDocumentLocation, final QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}

	/**
	 * Este metodo sera chamado para produzir um proxy da interface Reservation que fará a ponte entre o client e o webservice.
	 */
	@WebEndpoint
	public Reservation getReservationPort() {
		return super.getPort(new QName(NAMESPACE, "ReservationPort"), Reservation.class);
	}

}
