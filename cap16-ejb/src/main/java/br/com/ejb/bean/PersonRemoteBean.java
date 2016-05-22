package br.com.ejb.bean;

import javax.ejb.Remote;
import java.io.Serializable;

@Remote
public interface PersonRemoteBean extends Serializable {

	String message();

}
