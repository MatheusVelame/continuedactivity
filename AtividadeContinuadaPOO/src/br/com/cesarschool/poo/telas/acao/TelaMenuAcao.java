package br.com.cesarschool.poo.telas.acao;

import java.time.LocalDate;
import java.util.Scanner;

import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import br.com.cesarschool.poo.telas.TelaMenuPrincipal;
import br.com.cesarschool.poo.titulos.entidades.Acao;

public class TelaMenuAcao {
    private final Scanner scanner = new Scanner(System.in);
    private final MediatorAcao mediatorAcao = new MediatorAcao();
    private final TelaMenuPrincipal menuPrincipal;
    private final TelaBuscarAcao telaBuscaAcao;
    private final TelaExcluirAcao telaExcluirAcao;
    
    public TelaMenuAcao(TelaMenuPrincipal menuPrincipal, TelaBuscarAcao telaBuscaAcao) {
        this.menuPrincipal = menuPrincipal;
        this.telaBuscaAcao = new TelaBuscarAcao(this); 
		this.telaExcluirAcao = new TelaExcluirAcao();
    }
    
    
   public void menuAcao() {
        int opcaoAcao;

        do {
            System.out.println("\nGerenciar Ação:");
            System.out.println("1. Incluir Ação");
            System.out.println("2. Alterar Ação");
            System.out.println("3. Excluir Ação");
            System.out.println("4. Buscar Ação");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoAcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoAcao) {
                case 1 -> incluirAcao();
                case 2 -> alterarAcao();
                case 3 -> telaExcluirAcao.excluirAcao();
                case 4 -> telaBuscaAcao.buscarAcao();
                case 0 -> menuPrincipal.exibirMenuPrincipal();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcaoAcao != 0);
    }

    private void incluirAcao() {
        System.out.println("\nIncluir Ação");
        
        Acao acao = lerAcao();
        if (acao == null) {
            System.out.println("Erro: Não foi possível ler os dados da ação.");
            return;
        }

        String erro;
        try {
            erro = mediatorAcao.incluir(acao);
        } catch (Exception e) {
            System.out.println("Erro ao incluir ação: " + e.getMessage());
            return;
        }

        if (erro == null) {
            System.out.println("Ação incluída com sucesso!");
        } else {
            System.out.println(erro);
        }
    }


    private void alterarAcao() {
        System.out.println("\nAlterar Ação");
        Acao acao = lerAcao();
        String erro;
		try {
			erro = mediatorAcao.alterar(acao);
		} catch (Exception e) {
			System.out.println("Erro ao incluir ação: " + e.getMessage());
            return;
		}
        if (erro == null) {
            System.out.println("Ação alterada com sucesso!");
        } else {
            System.out.println("Erro: Ação não encontrada.");
        }
    }

    
    
    private Acao lerAcao() {
        System.out.print("Identificador: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Data de Validade (YYYY-MM-DD): ");
        LocalDate dataValidade = LocalDate.parse(scanner.nextLine());

        System.out.print("Valor Unitário: ");
        double valorUnitario = scanner.nextDouble();
        scanner.nextLine();

        return new Acao(id, nome, dataValidade, valorUnitario);
    }

    
    private void gerenciarOperacao() {
        System.out.println("Gerenciamento de Operação ainda não implementado.");
    }

    private void gerenciarTituloDivida() {
        System.out.println("Gerenciamento de Título de Dívida ainda não implementado.");
    }
}