package br.com.ejb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SumModel implements Serializable {

	private static final long serialVersionUID = 5806243435729708027L;
	private int num1;
	private int num2;

}
