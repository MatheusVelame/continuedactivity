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
