package br.com.cesarschool.poo.telas.acao;


import java.io.IOException;
import java.util.Scanner;



public class TelaMenuPrincipal {
	private final Scanner scanner = new Scanner(System.in);
	private final TelaMenuTituloDivida menuTituloDivida = new TelaMenuTituloDivida(this); 
    private final TelaMenuAcao menuAcao = new TelaMenuAcao(this);
    private final TelaOperacao menuOperacao = new TelaOperacao(this);
    public static void main(String[] args) throws IOException {
        TelaMenuPrincipal menu = new TelaMenuPrincipal();
        menu.exibirMenuPrincipal();
    }
    public void exibirMenuPrincipal() throws IOException {
        int opcao;
        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Ação");
            System.out.println("2. Gerenciar Título de Dívida");
            System.out.println("3. Gerenciar Operações");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> menuAcao.menuAcao();  
                case 2 -> menuTituloDivida.menuTitulo();
                case 3 -> menuOperacao.menuOperacao();  
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}