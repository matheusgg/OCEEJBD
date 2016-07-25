package br.com.ejb.ws;

import br.com.ejb.ws.client.ReservationClient;

import javax.ejb.Stateless;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceRef;
import java.math.BigDecimal;

@Stateless
public class ReservationConsumerBeanImpl implements ReservationConsumerRemoteBean {

	@WebServiceRef(ReservationClient.class)
	private Reservation reservation;

	@WebServiceRef
	private ReservationClient client;

	@Override
	public void consume() {
		final int reservationResponse = this.reservation.makeReservation("2334", 823642, new BigDecimal("100.00"), new Holder<Boolean>(null));
		final boolean reducedReservationResponse = this.reservation.makeReservationReduced(823642, new BigDecimal("100.00"));
		this.reservation.cancelReservations(823642);
	}
}
