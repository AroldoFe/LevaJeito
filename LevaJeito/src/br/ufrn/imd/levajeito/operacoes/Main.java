package br.ufrn.imd.levajeito.operacoes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Main {
	
	public static Hashtable<String, String> generateHash(String diretorio, String remover) throws IOException {
		Hashtable<String, String> mapeamento = new Hashtable<String, String>();
		
		ArrayList<String> arquivos = new ArrayList<String>();
		listarArquivos(arquivos, diretorio);
		
		for(String arquivo: arquivos) {
			String hash = LocalShell.executeHash(arquivo);
			mapeamento.put(arquivo, hash);
		}
		
		return mapeamento;
	}
	
	public static void generateHmac() {
		
	}
	
	public static void listarArquivos(ArrayList<String> arquivos, String diretorio){
		File files[] = new File(diretorio).listFiles();
	
		for(File file: files) {
			if(file.isDirectory())
				listarArquivos(arquivos, file.getAbsolutePath());
			else {
				arquivos.add(file.getAbsolutePath());
				//System.out.println(file.getAbsolutePath().replace(remover, ""));
			}
		}
	}

	public static ArrayList<String> toList(String[] args){
		ArrayList<String> argumentos = new ArrayList<String>();
		for(String argumento: args) {
			argumentos.add(argumento);
		}
		return argumentos;
	}
	
	// Funções para pegar os parâmetros da linha de comando
	
	
	
	
	// Main
	
	public static void main(String[] args) {
		ArrayList<String> argumentos = toList(args);
		try {
			String raiz = argumentos.get(argumentos.indexOf("-file")+1);
			
			System.out.println(raiz);
			System.out.println("--------- Listando os arquivos! ---------");
			
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Adicionar argumentos.");
		}
	}
}
