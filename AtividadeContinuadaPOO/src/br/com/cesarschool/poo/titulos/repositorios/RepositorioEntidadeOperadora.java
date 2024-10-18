package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas. (identificador, nome, dataValidade, valorUnitario)
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */

public class RepositorioEntidadeOperadora {
	
	public boolean incluir(Acao acao) {
		
		String objetosAcao = (acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario());
		boolean encontrarIdentificadorFlag = true;
	
		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))){
			String linha ;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(acao.getIdentificador() + ";")) {  // Usa startsWith para verificar o identificador
					encontrarIdentificadorFlag = false;
					break;
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Acao.txt", true))) {
				writer.write(objetosAcao);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean alterar(Acao acao) {
		
		String novosObjetosAcao = (acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataDeValidade() + ";" + acao.getValorUnitario());
		boolean encontrarIdentificadorFlag = false;
		StringBuilder conteudoArquivo = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))){
			String linha;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(acao.getIdentificador() + ";")) {  // Usa startsWith para verificar o identificador
					conteudoArquivo.append(novosObjetosAcao).append(System.lineSeparator());
					encontrarIdentificadorFlag = true;
				} else {
					 conteudoArquivo.append(linha).append(System.lineSeparator());  // Mantém as outras linhas inalteradas
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Acao.txt"))) {
				writer.write(conteudoArquivo.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean excluir(Acao acao) {
	
		boolean encontrarIdentificadorFlag = false;
		StringBuilder conteudoArquivo = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))){
			String linha;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(acao.getIdentificador() + ";")) {
					encontrarIdentificadorFlag = true;
				} else {
					conteudoArquivo.append(linha).append(System.lineSeparator());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Acao.txt"))) {
				writer.write(conteudoArquivo.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public Acao buscar(int identificador) {
		
		Acao acao = null;
	    String[] partes = null;

	    try (BufferedReader reader = new BufferedReader(new FileReader("Acao.txt"))){
	        String linha;
	        while((linha = reader.readLine()) != null) {
	            if (linha.startsWith(identificador + ";")) {
	                partes = linha.split(";");
	                break;    
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    if (partes != null && partes.length == 4) {  
	        acao = new Acao(identificador, partes[1], LocalDate.parse(partes[2]), (Double.parseDouble(partes[3]))); 
	    }
	    
	    return acao;
	}

}