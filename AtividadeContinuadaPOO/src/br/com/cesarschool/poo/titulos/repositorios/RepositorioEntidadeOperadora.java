package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

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
	
	public boolean incluir(EntidadeOperadora entidadeOperadora) {
		
		String objetosOperadora = (entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida());
		boolean encontrarIdentificadorFlag = true;
	
		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))){
			String linha ;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(entidadeOperadora.getIdentificador() + ";")) {  // Usa startsWith para verificar o identificador
					encontrarIdentificadorFlag = false;
					break;
				}
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("EntidadeOperadora.txt", true))) {
				writer.write(objetosOperadora);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean alterar(EntidadeOperadora entidadeOperadora) {
		
		String novosObjetosOperadora = (entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida());
		boolean encontrarIdentificadorFlag = false;
		StringBuilder conteudoArquivo = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))){
			String linha;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(entidadeOperadora.getIdentificador() + ";")) {  // Usa startsWith para verificar o identificador
					conteudoArquivo.append(novosObjetosOperadora).append(System.lineSeparator());
					encontrarIdentificadorFlag = true;
				} else {
					 conteudoArquivo.append(linha).append(System.lineSeparator());  // Mantém as outras linhas inalteradas
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("EntidadeOperadora.txt"))) {
				writer.write(conteudoArquivo.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean excluir(long identificador) {
	
		boolean encontrarIdentificadorFlag = false;
		StringBuilder conteudoArquivo = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))){
			String linha;
			while((linha = reader.readLine()) != null) {
				if (linha.startsWith(identificador + ";")) {
					encontrarIdentificadorFlag = true;
				} else {
					conteudoArquivo.append(linha).append(System.lineSeparator());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (encontrarIdentificadorFlag) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("EntidadeOperadora.txt"))) {
				writer.write(conteudoArquivo.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	public EntidadeOperadora buscar(long identificador) {
		
		EntidadeOperadora entidadeOperadora = null;
	    String[] partes = null;

	    try (BufferedReader reader = new BufferedReader(new FileReader("EntidadeOperadora.txt"))){
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
	    
	    if (partes != null && partes.length == 5) {  
	        entidadeOperadora = new EntidadeOperadora(identificador, partes[1], Boolean.parseBoolean(partes[2])); 
	    }
	    
	    return entidadeOperadora;
	}

}