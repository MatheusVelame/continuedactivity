package br.com.cesarschool.poo.titulos.telas;

import java.util.Scanner;



public class MenuPrincipal {
	private final Scanner scanner = new Scanner(System.in);
	private final MenuTituloDivida menuTituloDivida = new MenuTituloDivida(this); 
    private final MenuAcao menuAcao = new MenuAcao(this);
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.exibirMenuPrincipal();
    }

    public void exibirMenuPrincipal() {
        int opcao;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Ação");
            System.out.println("2. Gerenciar Título de Dívida");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> menuAcao.menuAcao();  
                case 2 -> menuTituloDivida.menuTitulo();  
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}