package br.ufrn.imd.levajeito.excecoes;

/**
 * Exceção responsável por indicar que a senha do hmac não é valida
 * @author aroldo-felix
 *
 */
public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException(String message) {
		super(message);
	}

}
