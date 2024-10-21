package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
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

public class RepositorioAcao {

	public boolean incluir(Acao acao) throws Exception {
		
		try {
			List<String> linhas = Files.readAllLines(Paths.get("Acao.txt"));
			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id == acao.getIdentificador()) {
					return false;
				}
			}

			String novaLinha = acao.getIdentificador() + ";" + acao.getNome() + ";" +
					acao.getDataDeValidade() + ";" + acao.getValorUnitario();
			Files.write(Paths.get("Acao.txt"), Collections.singletonList(novaLinha), StandardOpenOption.APPEND);
			return true;

		} catch (IOException e) {
			throw new Exception("Erro ao incluir ação no arquivo: " + e.getMessage(), e);
		}
	}

	

	public boolean alterar(Acao acao) throws Exception {
		
		
			try {
			List<String> linhas = Files.readAllLines(Paths.get("Acao.txt"));
			boolean encontrado = false;
			List<String> novasLinhas = new ArrayList<>();

			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id == acao.getIdentificador()) {
					linha = acao.getIdentificador() + ";" + acao.getNome() + ";" +
							acao.getDataDeValidade() + ";" + acao.getValorUnitario();
					encontrado = true;
				}

				novasLinhas.add(linha);
			}

			if (encontrado) {
				Files.write(Paths.get("Acao.txt"), novasLinhas);
				return true;
			}
			return false;

		} catch (IOException e) {
			throw new Exception("Erro ao alterar ação no arquivo: " + e.getMessage(), e);
		}
	}

	public boolean excluir(int identificador) throws Exception {
		try {
			List<String> linhas = Files.readAllLines(Paths.get("Acao.txt"));
			boolean encontrado = false;
			List<String> novasLinhas = new ArrayList<>();

			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id != identificador) {
					novasLinhas.add(linha);
				} else {
					encontrado = true;
				}
			}

			if (encontrado) {
				Files.write(Paths.get("Acao.txt"), novasLinhas);
				return true;
			}
			return false;

		} catch (IOException e) {
			throw new Exception("Erro ao excluir ação no arquivo: " + e.getMessage(), e);
		}
	}

	public Acao buscar(int identificador) throws Exception {
	    try {
	        List<String> linhas = Files.readAllLines(Paths.get("Acao.txt"));
	        for (String linha : linhas) {
	            String[] partes = linha.split(";");
	            if (partes.length < 4) {
	                continue; 
	            }

	            int id = Integer.parseInt(partes[0]);
	            if (id == identificador) {
	                String nome = partes[1];
	                LocalDate dataValidade = LocalDate.parse(partes[2]);
	                double valorUnitario = Double.parseDouble(partes[3]);
	                return new Acao(id, nome, dataValidade, valorUnitario);
	            }
	        }
	    } catch (IOException e) {
	        throw new Exception("Erro ao buscar ação no arquivo: " + e.getMessage(), e);
	    }
	    return null; 
	}
	}
