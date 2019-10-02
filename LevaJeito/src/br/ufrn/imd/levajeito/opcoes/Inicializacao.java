package br.ufrn.imd.levajeito.opcoes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import br.ufrn.imd.levajeito.excecoes.InvalidOutputFileException;
import br.ufrn.imd.levajeito.excecoes.InvalidPasswordException;

/**
 * Classe responsável por iniciar a guarda da pasta
 * @author aroldo-felix
 */
public class Inicializacao extends Opcao {

	/**
	 * Método responsável por criar o diretório oculto
	 * @throws IOException
	 */
	private void criarDiretorioOculto() throws IOException {		
		String caminho = tratarCaminho(getDiretorio()) + ".guarda/";
		
		File diretorio = new File(caminho);
		if(!diretorio.exists()) {
			StringBuffer command = new StringBuffer();
			command.append("mkdir ");
			command.append(caminho);
						
			super.getLocalShell().executeCommand(command.toString());	
		}
	}
	
	/**
	 * Método responsável por criar o arquivo que guardará as Hash's	
	 * @throws IOException
	 */
	private void criarArquivo() throws IOException {
		String caminho = tratarCaminho(getDiretorio()) + ".guarda/" + getMetodo() + ".txt";
		String comando = "echo > " + caminho;
		
		super.getLocalShell().executeCommand(comando);
	}
	
	/**
	 * Método responsável por realizar a função de guardar o diretório
	 */
	@Override
	public void executar() throws IOException, InvalidPasswordException, InvalidOutputFileException {
		// Listar os arquivos do diretório
		ArrayList<String> arquivos = new ArrayList<String>(); 
		super.listarArquivos(arquivos, super.getDiretorio());
		
		// Gerar as hash's
		Hashtable<String, String> hashs = super.gerarHashs(arquivos);
		
		// Criar o Diretório oculto
		criarDiretorioOculto();
		
		// Criar o arquivo para salvar hashs na pasta oculta
		criarArquivo();
		
		// Gerar o arquivo que vai guardar as hashs
		salvarHashs(hashs);
		
		// Gerar relatório
		StringBuffer relatorio = new StringBuffer(); 
		relatorio.append("Pasta guardada com sucesso!\n");
		relatorio.append("Método: " + getMetodo() + "\n");
		relatorio.append("Salvo em: " + getDiretorio() + ".guarda/" + getMetodo() + ".txt"); 
		
		gerarRelatorio(relatorio.toString());
	}
}
