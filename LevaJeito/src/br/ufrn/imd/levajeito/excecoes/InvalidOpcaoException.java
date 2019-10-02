package br.ufrn.imd.levajeito.excecoes;

/**
 * Exceção que indica que os argumentos -i, -t ou -x não foram passados
 * @author aroldo-felix
 */
public class InvalidOpcaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidOpcaoException(String message) {
		super(message);
	}
}
