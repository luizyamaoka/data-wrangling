package br.com.contaazul.model;

@SuppressWarnings("serial")
public class UnavailableAPIException extends RuntimeException {

	public UnavailableAPIException(String message) {
		super(message);
	}
	
}
