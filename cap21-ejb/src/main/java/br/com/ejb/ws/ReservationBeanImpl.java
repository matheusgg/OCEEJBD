package br.com.ejb.ws;

import lombok.extern.java.Log;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import java.math.BigDecimal;

/**
 * Neste caso, o contrato do servico esta separado da implementacao, desta forma é preciso definir a anotacao @WebService nos dois pontos.
 * Porém, é permitido especificar os atributos da anotacao apenas na classe que implementa o contrato.
 */
@Log
@Stateless
@WebService(name = "ReservationBean",
		portName = "ReservationPort",
		serviceName = "ReservationService",
		targetNamespace = "http://reservation.com",
		endpointInterface = "br.com.ejb.ws.Reservation")
public class ReservationBeanImpl implements Reservation {

	public int makeReservation(final String key, final int clientId, final BigDecimal amount, final Holder<Boolean> response) {
		log.info("============== makeReservation ==============");
		log.info(key);
		log.info(String.valueOf(clientId));
		log.info(amount.toString());
		response.value = true;
		return clientId;
	}

	public boolean makeReservationReduced(final int clientId, final BigDecimal amount) {
		log.info("============== makeReservationReduced ==============");
		log.info(String.valueOf(clientId));
		log.info(amount.toString());
		return true;
	}

	public void cancelReservations(final int clientId) {
		log.info("============== cancelReservations ==============");
		log.info(String.valueOf(clientId));
	}

}
