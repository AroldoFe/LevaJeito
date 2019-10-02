package br.ufrn.imd.levajeito.opcoes;

import java.io.File;
import java.io.IOException;

import br.ufrn.imd.levajeito.excecoes.InvalidOutputFileException;
/**
 * Classe responsável por apagar o rastreio de uma pasta 
 * @author aroldo-felix
 */
public class Desativacao extends Opcao {

	/**
	 * Método para apagar a pasta oculta que guarda o rastreamento
	 */
	@Override
	public void executar() throws InvalidOutputFileException, IOException {
		String diretorio = getDiretorio() + ".guarda/";
		
		// Verificar se a pasta oculta existe
		File file = new File(diretorio);
		
		// Apagar a pasta
		String relatorio = new String();
		if(file.exists() && file.isDirectory()) {
			String comando = "rm -R " + tratarCaminho(diretorio);
			super.getLocalShell().executeCommand(comando);
			relatorio = "Concluido: A pasta não está sendo mais guardada!";
		} else {
			relatorio = "A pasta não estava sendo guardada!";
		}
		
		// Gerar relatório
		gerarRelatorio(relatorio);
	}

}
