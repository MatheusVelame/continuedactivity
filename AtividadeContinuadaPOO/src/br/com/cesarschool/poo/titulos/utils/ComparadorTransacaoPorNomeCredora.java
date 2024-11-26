package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

/*
 * Herda de ComparadorPadrao. Deve implementar
 * Comparador, e o critério de comparação que ela deve usar
 * é o de nome da entidade credora.
 */


public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao implements Comparador {

	@Override
	public int comparar(Comparavel c1, Comparavel c2) {
		
		String primeiroNome = ((Transacao) c1).getEntidadeCredito().getNome();
		String segundoNome = ((Transacao) c2).getEntidadeCredito().getNome();
		
		return primeiroNome.compareTo(segundoNome);
	}
	
}
