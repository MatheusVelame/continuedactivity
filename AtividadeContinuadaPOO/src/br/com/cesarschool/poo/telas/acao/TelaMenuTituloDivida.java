package br.com.cesarschool.poo.telas.acao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;


public class TelaMenuTituloDivida {
	private final Scanner scanner = new Scanner(System.in);
    private final MediatorTituloDivida mediatorTitulo = MediatorTituloDivida.getInstancia();
    private final TelaMenuPrincipal menuPrincipal;

    
    public TelaMenuTituloDivida(TelaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
    
    
    
   public void menuTitulo() throws IOException{
    int opcaoTitulo;

    do {
        System.out.println("\nGerenciar Titulo:");
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
	System.out.println("\nIncluir titulo");
    
    TituloDivida titulo = lerTitulo();
    if (titulo == null) {
        System.out.println("Erro: Não foi possível ler os dados do titulo.");
        return;
    }

    String erro;
    try {
        erro = mediatorTitulo.incluir(titulo);
    } catch (Exception e) {
        System.out.println("Erro ao incluir titulo: " + e.getMessage());
        return;
    }

    if (erro == null) {
        System.out.println("titulo incluído com sucesso!");
    } else {
        System.out.println(erro);
    }    
}

	private TituloDivida alterarTitulo() {
	    System.out.println("\nAlterar titulo");
	    try {
	        TituloDivida titulo = lerTitulo();
	        String erro = mediatorTitulo.alterar(titulo);
	        if (erro == null) {
	            System.out.println("titulo alterado com sucesso!");
	        } else {
	            System.out.println(erro);
	        }
	    } catch (Exception e) {
	        System.out.println("Erro ao alterar o titulo: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private void excluirTitulo() {
	    try {
	        System.out.println("\nExcluir titulo");
	        System.out.print("Informe o identificador do titulo: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();
	        
	        String erro = mediatorTitulo.excluir(id);
	        if (erro == null) {
	            System.out.println("titulo excluído com sucesso!");
	        } else {
	            System.out.println(erro);
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
	        System.out.println("\nBuscar titulo");
	        System.out.print("Informe o identificador do titulo: ");
	        
	        int id = scanner.nextInt();
	        scanner.nextLine();  

	        TituloDivida titulo = mediatorTitulo.buscar(id);

	        if (titulo != null) {
	            System.out.println("titulo encontrado: " + titulo);
	        } else {
	            System.out.println("Erro: titulo não encontrada.");
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

    