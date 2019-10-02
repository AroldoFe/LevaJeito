package br.ufrn.imd.levajeito.excecoes;

/**
 * Exceção que indica que o diretório não foi passado corretamente.
 * @author aroldo-felix
 */
public class InvalidDiretorioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidDiretorioException(String message) {
		super(message);
	}
}
