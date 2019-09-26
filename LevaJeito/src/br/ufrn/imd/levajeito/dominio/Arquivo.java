package br.ufrn.imd.levajeito.dominio;

import java.util.List;

public class Arquivo {
	private String nome;
	private Arquivo pai;
	private String hash;
	private List<Arquivo> filhos;
}
