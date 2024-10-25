package br.com.cesarschool.poo.telas.acao;

import java.io.IOException;
import java.util.Scanner;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

public class TelaOperacao {

	private final Scanner scanner = new Scanner(System.in);
	private final MediatorOperacao mediatorOperacao = MediatorOperacao.getUnicaInstancia();
	private final TelaMenuPrincipal menuPrincipal; 

	public TelaOperacao(TelaMenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}
	
	public void menuOperacao() throws IOException {
		int opcao;
		
		do {
            System.out.println("\nGerenciar Operação:");
            System.out.println("1. Realizar Operação");
            System.out.println("2. Gerar Extrato");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> realizarOperacao();
                case 2 -> gerarExtrato();
                case 0 -> menuPrincipal.exibirMenuPrincipal();
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
	}
	
	private void realizarOperacao() throws IOException {
		System.out.println("\nRealizar Operação");
		
		System.out.println("É uma ação? (sim/nao)");
		String ehAcao = scanner.nextLine().trim().toLowerCase();
		boolean ehAcaoBoolean;
		if (ehAcao.equals("sim")) {
			ehAcaoBoolean = true;
		} else {
			ehAcaoBoolean = false;
		}
		
		System.out.print("Digite o identificador da entidade de credito: ");
    	int entidadeCredito = scanner.nextInt();
    	
    	System.out.print("Digite o identificador da entidade de debito: ");
    	int idEntidadeDebito = scanner.nextInt();
    	
    	System.out.print("Digite o identificador da acao ou titulo: ");
    	int idAcaoOuTitulo = scanner.nextInt();
    	
    	System.out.print("Digite o valor: ");
    	double valor = scanner.nextDouble();
    	
    	System.out.println(mediatorOperacao.realizarOperacao(ehAcaoBoolean, entidadeCredito, idEntidadeDebito, idAcaoOuTitulo, valor));
	}
	
	private void gerarExtrato() {
		System.out.println("\nGerar Extrato");
		
		Transacao[] transacoes;
		
		System.out.print("Digite o identificador da entidade: ");
    	int entidade = scanner.nextInt();
    	
    	transacoes = mediatorOperacao.gerarExtrato(entidade);
    	
    	if (transacoes.length == 0) {
            System.out.println("Nenhuma transação encontrada para essa entidade.");
        } else {
            System.out.println("Extrato de transações:");
            for (Transacao transacao : transacoes) {
                System.out.println("--------------------------------------------------");
                System.out.println("Entidade de Crédito: " + transacao.getEntidadeCredito().getNome());
                System.out.println("Entidade de Débito: " + transacao.getEntidadeDebito().getNome());
                if (transacao.getAcao() != null) {
                    System.out.println("Ação: " + transacao.getAcao().getNome());
                } else if (transacao.getTituloDivida() != null) {
                    System.out.println("Título de Dívida: " + transacao.getTituloDivida().getNome());
                }
                System.out.println("Valor da Operação: " + transacao.getValorOperacao());
                System.out.println("Data e Hora da Operação: " + transacao.getDataHoraOperacao());
            }
            System.out.println("--------------------------------------------------");
        }

	}
	
}