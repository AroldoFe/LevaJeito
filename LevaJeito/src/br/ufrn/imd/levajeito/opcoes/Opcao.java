package br.ufrn.imd.levajeito.opcoes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import br.ufrn.imd.levajeito.excecoes.InvalidOutputFileException;
import br.ufrn.imd.levajeito.excecoes.InvalidPasswordException;
import br.ufrn.imd.levajeito.shell.LocalShell;

/**
 * Classe generalista para realizar opções
 * @author aroldo-felix
 */
public abstract class Opcao {
	private String metodo; // hash ou hmac
	private String password;
	private String diretorio;
	private String saida;
	private final LocalShell localShell = new LocalShell();
	
	/**
	 * Método abstrato polimófico
	 * @throws IOException
	 * @throws InvalidPasswordException
	 * @throws InvalidOutputFileException
	 */
	public abstract void executar() throws IOException, InvalidPasswordException, InvalidOutputFileException;

	/**
	 * Método responsável por listar os arquivos dentro da pasta recursivamente
	 * @param arquivos
	 * @param diretorio
	 */
	public void listarArquivos(ArrayList<String> arquivos, String diretorio){
		File files[] = new File(diretorio).listFiles();
	
		for(File file: files) {
			if( file.isDirectory() ) {
				if(!file.getName().startsWith(".guarda")) {
					listarArquivos(arquivos, file.getAbsolutePath());
				}
			}
			else {
				arquivos.add(file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Método responsável por tratar o caminho e deixar no padrão linux
	 * @param caminho
	 * @return
	 */
	public String tratarCaminho(String caminho) {
		return caminho.replace(" ", "\\ "); 
	}
	
	/**
	 * Método responsável por tratar o comando antes de executá-lo
	 * @param arquivo
	 * @return
	 * @throws InvalidPasswordException
	 */
	public String tratarComando(String arquivo) throws InvalidPasswordException {
		StringBuffer commandBuffer = new StringBuffer();
		commandBuffer.append("openssl dgst -sha256 ");
		
		if(metodo.equals("hmac")) {
			if(password == null || password.equals("")) {
				throw new InvalidPasswordException("Erro: Password inválida para o método -hmac.");
			}
			commandBuffer.append("-hmac \"" + getPassword() + "\" ");
		}
		commandBuffer.append(tratarCaminho(arquivo));
		
		return commandBuffer.toString();
	}
	
	/**
	 * Método responsável por gerar as hash's de todos os arquivos da pasta
	 * @param arquivos
	 * @return
	 * @throws IOException
	 * @throws InvalidPasswordException
	 */
	public Hashtable<String, String> gerarHashs(ArrayList<String> arquivos) throws IOException, InvalidPasswordException {
		Hashtable<String, String> mapeamento = new Hashtable<String, String>();
		
		for(String arquivo: arquivos) {
			String command = tratarComando(arquivo);
			
			String hash = localShell.executeCommand(command).split("= ")[1];
			String guardarArquivo = arquivo.replace(this.diretorio, "");
			
			mapeamento.put(guardarArquivo, hash);
		}
		
		return mapeamento;
	}
	
	/**
	 * Método responsável por guardar as hash's dentro de um arquivo na pasta oculta
	 * @param hashs
	 * @throws IOException
	 */
	public void salvarHashs(Hashtable<String, String> hashs) throws IOException {
		String path = getDiretorio() + ".guarda/" + getMetodo() + ".txt";
		
		FileWriter arq = new FileWriter(path);
		BufferedWriter buffWrite = new BufferedWriter(arq);
		Set<String> chaves = hashs.keySet();
		
		for(String chave : chaves) {
			buffWrite.append(chave + "<-->" + hashs.get(chave) + "\n");
		}
		
		buffWrite.close();
		arq.close();
	}
	
	/**
	 * Método responsável por gerar o relatório
	 * @param relatorio
	 * @throws InvalidOutputFileException
	 * @throws IOException
	 */
	public void gerarRelatorio(String relatorio) throws InvalidOutputFileException, IOException {
		if(saida == null || saida.equals("")) {
			System.out.println(relatorio);
			return ;
		} else if( saida.endsWith(".txt") ) {
			FileWriter arq = new FileWriter(saida);
			PrintWriter gravarArq = new PrintWriter(arq);
			
			gravarArq.print(relatorio);
			
			arq.close();
		} else {
			throw new InvalidOutputFileException("Erro: Arquivo de saída inválido. Tem que ser um arquivo .txt!");	
		}
	}
	
	// Gets e Sets
	
	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}
	
	public LocalShell getLocalShell() {
		return localShell;
	}
}
