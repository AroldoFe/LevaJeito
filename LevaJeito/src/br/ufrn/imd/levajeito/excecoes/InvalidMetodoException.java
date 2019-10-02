package br.ufrn.imd.levajeito.excecoes;
/**
 * Exceção que indica que nem o método hash nem o hmac foram escolhidos
 * @author aroldo-felix
 */
public class InvalidMetodoException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidMetodoException(String message) {
		super(message);
	}
}
