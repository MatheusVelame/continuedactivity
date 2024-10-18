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
		String erroValidacao = validar(acao);
		if (erroValidacao != null) {
			throw new Exception(erroValidacao);
		}

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
		String erroValidacao = validar(acao);
		if (erroValidacao != null) {
			throw new Exception(erroValidacao);
		}

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
				int id = Integer.parseInt(partes[0]);

				if (id == identificador) {
					String nome = partes[1];
					LocalDate dataValidade = LocalDate.parse(partes[2]);
					double valorUnitario = Double.parseDouble(partes[3]);
					return new Acao(id, nome, dataValidade, valorUnitario);
				}
			}

			return null;

		} catch (IOException e) {
			throw new Exception("Erro ao buscar ação no arquivo: " + e.getMessage(), e);
		}
	}

	private String validar(Acao acao) {
		if (acao.getIdentificador() < 0 || acao.getIdentificador() > 100000) {
			return "Identificador deve estar entre 1 e 99999.";
		} else if (acao.getNome() == null || acao.getNome().isEmpty()) {
			return "Nome deve ser preenchido.";
		} else if (acao.getNome().length() < 10 || acao.getNome().length() > 100) {
			return "Nome deve ter entre 10 e 100 caracteres.";
		} else if (acao.getDataDeValidade() == null || acao.getDataDeValidade().isBefore(LocalDate.now().plusDays(30))) {
			return "Data de validade deve ter pelo menos 30 dias à frente da data atual.";
		} else if (acao.getValorUnitario() <= 0) {
			return "Valor unitário deve ser maior que zero.";
		}
		return null;
	}
}
