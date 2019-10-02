package br.ufrn.imd.levajeito.main;

import java.io.IOException;
import java.util.ArrayList;

import br.ufrn.imd.levajeito.excecoes.InvalidDiretorioException;
import br.ufrn.imd.levajeito.excecoes.InvalidMetodoException;
import br.ufrn.imd.levajeito.excecoes.InvalidOpcaoException;
import br.ufrn.imd.levajeito.excecoes.InvalidOutputFileException;
import br.ufrn.imd.levajeito.excecoes.InvalidPasswordException;
import br.ufrn.imd.levajeito.opcoes.Desativacao;
import br.ufrn.imd.levajeito.opcoes.Inicializacao;
import br.ufrn.imd.levajeito.opcoes.Opcao;
import br.ufrn.imd.levajeito.opcoes.Rastreamento;

/**
 * Classe responsável por identificar os fluxos de opções e preparar o ambiente
 * @author aroldo-felix
 */
public class Main {
	/**
	 * Generalização da opção escolhida
	 */
	private static Opcao opcao;
	/**
	 * Argumentos passados como parâmetro
	 */
	private static ArrayList<String> argumentos;
	
	/**
	 * Método para transformar os argumentos em uma lista 
	 * @param args, argumentos passados ao executar o programa 
	 * @return ArrayList dos argumentos
	 */
	public static ArrayList<String> toList(String[] args){
		ArrayList<String> argumentos = new ArrayList<String>();
		for(String argumento: args) {
			argumentos.add(argumento);
		}
		return argumentos;
	}
	
	// Funções para pegar os parâmetros da linha de comando
	
	/**
	 * Método para identificar qual a opção que foi escolhida
	 * @param argumentos
	 * @return Opção escolhida
	 * @throws InvalidOpcaoException, Exceção lançada se -i, -t ou -x não foi passada
	 */
	public static Opcao getOpcao(ArrayList<String> argumentos) throws InvalidOpcaoException {
		if(argumentos.indexOf("-i") != -1) {
			return new Inicializacao();
		} else if(argumentos.indexOf("-t") != -1) {
			return new Rastreamento();
		} else if(argumentos.indexOf("-x") != -1) {
			return new Desativacao();
		} else {
			throw new InvalidOpcaoException("Erro: Opção -i, -t ou -x não encontrada!");
		}
	}
	
	/**
	 * Método para pegar o diretório que se deseja guardar ou rastrear
	 * @return caminho absoluto do diretório
	 * @throws InvalidDiretorioException
	 */
	public static String getDiretorio() throws InvalidDiretorioException {
		String diretorio;
		if(opcao.getClass() == Inicializacao.class) {
			diretorio = argumentos.get(argumentos.indexOf("-i")+1);
		} else if(opcao.getClass() == Rastreamento.class) {
			diretorio = argumentos.get(argumentos.indexOf("-t")+1);
		} else if(opcao.getClass() == Desativacao.class) {
			diretorio = argumentos.get(argumentos.indexOf("-x")+1);
		} else {
			throw new InvalidDiretorioException("Erro: Diretório não passado como parâmetro.");
		}
		
		if(!diretorio.endsWith("/")) {
			diretorio += "/";
		}
		return diretorio;
	}
	
	/**
	 * Método para pegar nos argumentos o método hash ou hmac
	 * @return o método escolhido
	 * @throws InvalidMetodoException, se hash ou hmac não for passado
	 */
	public static String getMetodo() throws InvalidMetodoException {
		if(argumentos.indexOf("-hash") != -1) {
			return "hash";
		} else if(argumentos.indexOf("-hmac") != -1) {
			return "hmac";
		} else {
			throw new InvalidMetodoException("Erro: Método -hash ou -hmac não encontrados!");
		}
	}
	
	/**
	 * Método para pegar a senha para o hmac
	 * @return senha
	 */
	public static String getSenha() {
		if(opcao.getMetodo().equals("hmac")) {
			return argumentos.get(argumentos.indexOf("--hmac") + 1);
		} else {
			return null;
		}
	}
	
	/**
	 * Método para pegar o arquivo de saída do relatório
	 * @return arquivo de saída
	 */
	public static String getSaida() {
		if(argumentos.indexOf("-o") != -1 && argumentos.indexOf("-o") != argumentos.size()-1) {
			return argumentos.get(argumentos.indexOf("-o") + 1);
		}
		return null;
	}
	
	// Main
	
	public static void main(String[] args) {
		argumentos = toList(args);
		
		try {
			opcao = getOpcao(argumentos);
			opcao.setDiretorio(getDiretorio());
			opcao.setSaida(getSaida());			
			
			if(opcao.getClass() != Desativacao.class) {
				opcao.setMetodo(getMetodo());
				opcao.setPassword(getSenha());				
			} 
			
			opcao.executar();
		} 
		catch (InvalidOpcaoException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidDiretorioException e) {
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidOutputFileException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidMetodoException e) {
			System.out.println(e.getMessage());
		}
	}
}
