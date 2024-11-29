package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {

	public static void ordenar(Comparavel[] ents, Comparador comp) { // O primeiro ordena o array de entrada perguntando ao comparador se dois elementos são maiores, menores ou iguais.
		 
		int n = 1;
		int troca = 1;
		
		while (n <= ents.length && troca == 1) {
			troca = 0;
			for (int i=0 ; i<ents.length-1 ; i++) {
				if (comp.comparar(ents[i], ents[i+1]) > 0) {
					Comparavel aux = ents[i];
					ents[i] = ents[i+1];
					ents[i+1] = aux;
					troca = 1;
				}
			}
		}
		
	}
	
	public static void ordenar(Comparavel[] comps) { // O segundo ordena o array de entrada perguntando a um elemento se ele é maior, menor ou igual a outro elemento.
		
		
		int n = 1;
		int troca = 1;
		
		while (n <= comps.length && troca == 1) {
			troca = 0;
			for (int i=0 ; i<comps.length-1 ; i++) {
				if (comps[i].comparar(comps[i+1]) > 0) {
					Comparavel aux = comps[i];
					comps[i] = comps[i+1];
					comps[i+1] = aux;
					troca = 1;
				}
			}
		}
		
	}
	
}
