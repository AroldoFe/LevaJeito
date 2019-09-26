package br.ufrn.imd.levajeito.operacoes;

public class Main {
	
	public void main(String[] args) {
		
		try {
			System.out.println(args[1]);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Erro: formato incorreto de argumentos.");
			System.out.println("java Main -i ");
			System.out.println("java Main -t");
			System.out.println("java Main -x [filename]");
		}

	}

}
