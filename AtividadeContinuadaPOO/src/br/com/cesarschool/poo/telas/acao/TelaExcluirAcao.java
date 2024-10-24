package br.com.cesarschool.poo.telas.acao;

import java.util.Scanner;

import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaExcluirAcao {
	private final MediatorAcao mediatorAcao = new MediatorAcao();
	private final Scanner scanner = new Scanner(System.in);
	
	public void excluirAcao() {
        System.out.println("\nExcluir Ação");
        System.out.print("Informe o identificador da ação: ");
       
		int id = scanner.nextInt();
        scanner.nextLine();
        String erro;
		try {
			erro = mediatorAcao.excluir(id);
		} catch (Exception e) {
			System.out.println("Erro ao incluir ação: " + e.getMessage());
            return;
		}
        if (erro == null ) {
            System.out.println("Ação excluída com sucesso!");
        } else {
            System.out.println("Erro: Ação não encontrada.");
        }
    }

}
