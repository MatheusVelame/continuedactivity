package br.com.cesarschool.poo.titulos.telas;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;


public class MenuTituloDivida {
	private final Scanner scanner = new Scanner(System.in);
    private final RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
    private final MenuPrincipal menuPrincipal;

    
    public MenuTituloDivida(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
   
    
    
    
   public void menuTitulo(){
    int opcaoTitulo;

    do {
        System.out.println("\nGerenciar Ação:");
        System.out.println("1. Incluir titulo");
        System.out.println("2. Alterar titulo");
        System.out.println("3. Excluir titulo");
        System.out.println("4. Buscar titulo");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
        opcaoTitulo = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoTitulo) {
            case 1 -> incluirTitulo();
            case 2 -> alterarTitulo();
            case 3 -> excluirTitulo();
            case 4 -> buscarTitulo();
            case 0 -> menuPrincipal.exibirMenuPrincipal();
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcaoTitulo != 0);
}


private TituloDivida lerTitulo() {
    System.out.print("Identificador: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Nome: ");
    String nome = scanner.nextLine();

    System.out.print("Data de Validade (YYYY-MM-DD): ");
    LocalDate dataValidade = LocalDate.parse(scanner.nextLine());

    System.out.print("Taxa de juros: ");
    double taxaJuros = scanner.nextDouble();
    scanner.nextLine();

    return new TituloDivida(id, nome, dataValidade, taxaJuros);
}


private void incluirTitulo() {
	System.out.println("\nIncluir Ação");
    
    TituloDivida titulo = lerTitulo();
    if (titulo == null) {
        System.out.println("Erro: Não foi possível ler os dados da ação.");
        return;
    }

    boolean sucesso;
    try {
        sucesso = repositorioTituloDivida.incluir(titulo);
    } catch (Exception e) {
        System.out.println("Erro ao incluir ação: " + e.getMessage());
        return;
    }

    if (sucesso) {
        System.out.println("Ação incluída com sucesso!");
    } else {
        System.out.println("Erro: Ação já existente com esse identificador.");
    }    
}

	private TituloDivida alterarTitulo() {
	    System.out.println("\nAlterar Ação");
	    try {
	        TituloDivida titulo = lerTitulo();
	        boolean sucesso = repositorioTituloDivida.alterar(titulo);
	        if (sucesso) {
	            System.out.println("Ação alterada com sucesso!");
	        } else {
	            System.out.println("Erro: Ação não encontrada.");
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao alterar a ação: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private void excluirTitulo() {
	    try {
	        System.out.println("\nExcluir Ação");
	        System.out.print("Informe o identificador da ação: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();
	        
	        boolean sucesso = repositorioTituloDivida.excluir(id);
	        if (sucesso) {
	            System.out.println("Ação excluída com sucesso!");
	        } else {
	            System.out.println("Erro: Ação não encontrada.");
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Erro: Identificador inválido. Por favor, insira um número.");
	        scanner.nextLine(); 
	    } catch (Exception e) {
	        System.out.println("Erro inesperado: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private void buscarTitulo() {
	    try {
	        System.out.println("\nBuscar Ação");
	        System.out.print("Informe o identificador da ação: ");
	        
	        int id = scanner.nextInt();
	        scanner.nextLine();  

	        TituloDivida titulo = repositorioTituloDivida.buscar(id);

	        if (titulo != null) {
	            System.out.println("Ação encontrada: " + titulo);
	        } else {
	            System.out.println("Erro: Ação não encontrada.");
	        }
	    } catch (InputMismatchException e) {
	        System.out.println("Erro: Identificador inválido. Insira um número.");
	        scanner.nextLine(); 
	    } catch (Exception e) {
	        System.out.println("Erro inesperado: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	
}

    