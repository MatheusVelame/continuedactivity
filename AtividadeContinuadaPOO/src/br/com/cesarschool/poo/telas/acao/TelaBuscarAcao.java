package br.com.cesarschool.poo.telas.acao;

import java.util.Scanner;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;


public class TelaBuscarAcao {
	
	private final MediatorAcao mediatorAcao = new MediatorAcao();
	private final Scanner scanner = new Scanner(System.in);
	
	public TelaBuscarAcao(TelaMenuAcao telaMenuAcao) {
		
	}
	
	
	 void buscarAcao() {
        System.out.println("\nBuscar Ação");
        System.out.print("Informe o identificador da ação: ");
        
        int id = scanner.nextInt();

        Acao acao;
		try {
			acao = mediatorAcao.buscar(id);
		} catch (Exception e) {
			System.out.println("Erro ao incluir ação: " + e.getMessage());
            return;
		}

        if (acao != null) {
            System.out.println("Ação encontrada: " + acao);
        } else {
            System.out.println("Erro: Ação não encontrada.");
        }
    }


}
