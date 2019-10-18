package br.ufrn.imd.levajeito.opcoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import br.ufrn.imd.levajeito.excecoes.InvalidOutputFileException;
import br.ufrn.imd.levajeito.excecoes.InvalidPasswordException;

/**
 * Classe responsável por realizar o rastreamento de uma pasta salva
 * @author aroldo-felix
 *
 */
public class Rastreamento extends Opcao {

	/**
	 * Método responsável por dizer se a pasta oculta existe
	 */
	private boolean pastaOcultaExiste() {
		File pasta = new File(getDiretorio() + ".guarda/");
		return pasta.exists();
	}

	/**
	 * Método responsável por saber se já ouve um rastreamento para o método escolhido
	 */
	private boolean arquivoHashExiste() {
		File arquivo = new File(getDiretorio() + ".guarda/" + getMetodo() + ".txt");
		return arquivo.exists();
	}
	
	/**
	 * Método responsável por ler o arquivo em que as hash's foram salvas
	 * @return Hashtable que guarda o mapeamento nome_arquivo:hash
	 * @throws IOException
	 */
	private Hashtable<String, String> pegarHashs() throws IOException{
		Hashtable<String, String> hashs = new Hashtable<String, String>();
		// Fazer a leitura do arquivo
		String arquivoHash = getDiretorio() + ".guarda/" + getMetodo() + ".txt";
		BufferedReader br = new BufferedReader(new FileReader(arquivoHash));

	    while(br.ready()){
	    	String linha = br.readLine();
	    	if(linha != null && !linha.equals(""))
	    		hashs.put(linha.split("<-->")[0], linha.split("<-->")[1]);
	    }
	    br.close();
	    
	    return hashs;
	}
	
	/**
	 * Método responsável por comparar e atualizar a Hashtable do arquivo que foi lido com a que foi gerada
	 * @param lidas
	 * @param geradas
	 * @return
	 */
	private String compararHashs(Hashtable<String, String> lidas, Hashtable<String, String> geradas) {
		StringBuffer relatorio = new StringBuffer();
		
		Set<String> chavesLidas = lidas.keySet();
		Set<String> chavesGeradas = geradas.keySet();
		
		ArrayList<String> chavesRemover = new ArrayList<String>();
		
		// Verificando se todas as hashs lidas estão nas geradas
		for(String chaveLida: chavesLidas) {
			if(chavesGeradas.contains(chaveLida)) {
				if(!lidas.get(chaveLida).equals(geradas.get(chaveLida))) {
					relatorio.append("* Alterado: " + chaveLida + "\n");
				}
				lidas.put(chaveLida, geradas.get(chaveLida));
				chavesGeradas.remove(chaveLida);
			} else {
				chavesRemover.add(chaveLida);
				relatorio.append("- Excluído: " + chaveLida + "\n");
			}
		}
		
		// Verificando se existe alguem que foi gerado, mas não foi lido
		for(String chaveGerada: chavesGeradas) {
			lidas.put(chaveGerada, geradas.get(chaveGerada));
			relatorio.append("+ Inserido: " + chaveGerada + "\n");
		}
		
		// Removendo as hashs dos arquivos que foram removidos da pasta
		for(String remover: chavesRemover) {
			lidas.remove(remover);
		}
		
		return relatorio.toString();
	}
	
	/**
	 * Método responsável por realizar a varredura e atualizar o arquivo de hashs.
	 */
	@Override
	public void executar() throws IOException, InvalidPasswordException, InvalidOutputFileException {
		// Listar os arquivos do diretório
		ArrayList<String> arquivos = new ArrayList<String>();
		super.listarArquivos(arquivos, super.getDiretorio());
		
		// Gerar as hash's
		Hashtable<String, String> hashsGeradas = super.gerarHashs(arquivos);
		
		// Verificar se a pasta oculta e o arquivo existem
		String relatorio = "";
		if(pastaOcultaExiste() && arquivoHashExiste()) {
			// Ler o arquivo que guarda as hash's
			Hashtable<String, String> hashLidas = pegarHashs();
			
			// Comparar hash's geradas com as lidas
			relatorio = compararHashs(hashLidas, hashsGeradas);
			
			if(relatorio == null || relatorio.equals(""))
				relatorio = "Não houve alteração na pasta!\n";
			
			// Salvar as hashs
			salvarHashs(hashLidas);
		} else {
			relatorio = "Esta pasta não estava sendo guardada ou não estava sendo rastreada pelo método " + getMetodo() + "!\n";
		}
		
		// Gerar relatório
		gerarRelatorio(relatorio);
	}

}
