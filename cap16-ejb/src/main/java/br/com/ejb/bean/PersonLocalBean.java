package br.com.ejb.bean;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface PersonLocalBean extends Serializable {

	String message();

}
