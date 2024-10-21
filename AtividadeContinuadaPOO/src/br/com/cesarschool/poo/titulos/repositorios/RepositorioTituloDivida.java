package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
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

public class RepositorioTituloDivida {

	public boolean incluir(TituloDivida titulo) throws Exception {
		
		try {
			List<String> linhas = Files.readAllLines(Paths.get("TituloDivida.txt"));
			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id == titulo.getIdentificador()) {
					return false;
				}
			}

			String novaLinha = titulo.getIdentificador() + ";" + titulo.getNome() + ";" +
					titulo.getDataDeValidade() + ";" + titulo.getTaxaJuros();
			Files.write(Paths.get("TituloDivida.txt"), Collections.singletonList(novaLinha), StandardOpenOption.APPEND);
			return true;

		} catch (IOException e) {
			throw new Exception("Erro ao incluir título no arquivo: " + e.getMessage(), e);
		}
	}

	public boolean alterar(TituloDivida titulo) throws Exception {
		try {
			List<String> linhas = Files.readAllLines(Paths.get("TituloDivida.txt"));
			boolean encontrado = false;
			List<String> novasLinhas = new ArrayList<>();

			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id == titulo.getIdentificador()) {
					linha = titulo.getIdentificador() + ";" + titulo.getNome() + ";" +
							titulo.getDataDeValidade() + ";" + titulo.getTaxaJuros();
					encontrado = true;
				}

				novasLinhas.add(linha);
			}

			if (encontrado) {
				Files.write(Paths.get("TituloDivida.txt"), novasLinhas);
				return true;
			}
			return false;

		} catch (IOException e) {
			throw new Exception("Erro ao alterar título no arquivo: " + e.getMessage(), e);
		}
	}

	public boolean excluir(int identificador) throws Exception {
		try {
			List<String> linhas = Files.readAllLines(Paths.get("TituloDivida.txt"));
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
				Files.write(Paths.get("TituloDivida.txt"), novasLinhas);
				return true;
			}
			return false;

		} catch (IOException e) {
			throw new Exception("Erro ao excluir título no arquivo: " + e.getMessage(), e);
		}
	}

	public TituloDivida buscar(int identificador) throws Exception {
		try {
			List<String> linhas = Files.readAllLines(Paths.get("TituloDivida.txt"));

			for (String linha : linhas) {
				String[] partes = linha.split(";");
				int id = Integer.parseInt(partes[0]);

				if (id == identificador) {
					String nome = partes[1];
					LocalDate dataValidade = LocalDate.parse(partes[2]);
					double taxaJuros = Double.parseDouble(partes[3]);
					return new TituloDivida(id, nome, dataValidade, taxaJuros);
				}
			}

			return null;

		} catch (IOException e) {
			throw new Exception("Erro ao buscar título no arquivo: " + e.getMessage(), e);
		}
	}
}
