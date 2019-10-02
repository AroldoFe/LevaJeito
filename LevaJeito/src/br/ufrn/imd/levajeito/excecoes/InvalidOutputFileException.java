package br.ufrn.imd.levajeito.excecoes;

/**
 * Exeção que indica que o arquivo de saída não é txt
 * @author aroldo-felix
 */
public class InvalidOutputFileException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidOutputFileException(String message) {
		super(message);
	}
}
