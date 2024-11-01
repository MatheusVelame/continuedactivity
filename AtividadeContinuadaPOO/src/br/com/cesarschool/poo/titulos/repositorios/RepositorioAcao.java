package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.BufferedWriter;
import java.io.File;
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

public class RepositorioAcao {

    private static final String ARQUIVO_ACOES = "Acao.txt";

    // Método auxiliar para garantir que o arquivo existe
    private void garantirArquivo() {
        File file = new File(ARQUIVO_ACOES);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo Acao.txt criado.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }
    }

    public boolean incluir(Acao acao) {
        garantirArquivo();

        String objetoAcao = acao.getIdentificador() + ";" + acao.getNome() + ";" +
                            acao.getDataDeValidade() + ";" + acao.getValorUnitario();
        boolean identificadorJaExiste = false;

        // Verifica se o identificador já existe no arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(acao.getIdentificador() + ";")) {
                    identificadorJaExiste = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar duplicidade no arquivo: " + e.getMessage());
            return false;
        }

        if (!identificadorJaExiste) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES, true))) {
                writer.write(objetoAcao);
                writer.newLine();
                System.out.println("Ação gravada: " + objetoAcao);
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao incluir ação no arquivo: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Ação com identificador já existente.");
            return false;
        }
    }

    public boolean alterar(Acao acao) {
        garantirArquivo();

        String novoObjetoAcao = acao.getIdentificador() + ";" + acao.getNome() + ";" +
                                acao.getDataDeValidade() + ";" + acao.getValorUnitario();
        boolean identificadorEncontrado = false;
        StringBuilder conteudoArquivo = new StringBuilder();

        // Lê o arquivo e modifica a linha desejada
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(acao.getIdentificador() + ";")) {
                    conteudoArquivo.append(novoObjetoAcao).append(System.lineSeparator());
                    identificadorEncontrado = true;
                } else {
                    conteudoArquivo.append(linha).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo para alteração: " + e.getMessage());
            return false;
        }

        if (identificadorEncontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES))) {
                writer.write(conteudoArquivo.toString());
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao salvar alterações no arquivo: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean excluir(int identificador) {
        garantirArquivo();

        boolean identificadorEncontrado = false;
        StringBuilder conteudoArquivo = new StringBuilder();

        // Lê o arquivo e remove a linha com o identificador especificado
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(identificador + ";")) {
                    identificadorEncontrado = true;
                } else {
                    conteudoArquivo.append(linha).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo para exclusão: " + e.getMessage());
            return false;
        }

        if (identificadorEncontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ACOES))) {
                writer.write(conteudoArquivo.toString());
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao salvar exclusão no arquivo: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public Acao buscar(int identificador) {
        garantirArquivo();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ACOES))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(identificador + ";")) {
                    String[] partes = linha.split(";");
                    if (partes.length == 4) {
                        return new Acao(
                            identificador,
                            partes[1],
                            LocalDate.parse(partes[2]),
                            Double.parseDouble(partes[3])
                        );
                    } else {
                        System.err.println("Erro: Linha com identificador " + identificador + " não contém 4 partes.");
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter valor para double: " + e.getMessage());
        }

        return null;
    }
}
