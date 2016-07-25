package br.com.ejb.ws;

import javax.ejb.Remote;

@Remote
public interface ReservationConsumerRemoteBean {

	void consume();

}
