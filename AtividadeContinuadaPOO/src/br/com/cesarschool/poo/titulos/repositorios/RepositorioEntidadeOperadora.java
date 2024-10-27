package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.BufferedWriter;
import java.io.File;
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

    private static final String ARQUIVO_ENTIDADE = "EntidadeOperadora.txt";

    // Método auxiliar para garantir que o arquivo existe
    private void garantirArquivo() {
        File file = new File(ARQUIVO_ENTIDADE);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo EntidadeOperadora.txt criado.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            }
        }
    }

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        garantirArquivo();

        String novaLinha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                           entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
                           entidadeOperadora.getSaldoTituloDivida();

        // Verifica se o identificador já existe no arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ENTIDADE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(entidadeOperadora.getIdentificador() + ";")) {
                    return false;  // Identificador duplicado, não incluir
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao verificar duplicidade no arquivo: " + e.getMessage());
            return false;
        }

        // Adiciona a nova linha ao arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ENTIDADE, true))) {
            writer.write(novaLinha);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao incluir entidade operadora no arquivo: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) {
        garantirArquivo();

        String novaLinha = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" +
                           entidadeOperadora.getAutorizadoAcao() + ";" + entidadeOperadora.getSaldoAcao() + ";" +
                           entidadeOperadora.getSaldoTituloDivida();
        boolean identificadorEncontrado = false;
        StringBuilder conteudoArquivo = new StringBuilder();

        // Lê o arquivo e modifica a linha desejada
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ENTIDADE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(entidadeOperadora.getIdentificador() + ";")) {
                    conteudoArquivo.append(novaLinha).append(System.lineSeparator());
                    identificadorEncontrado = true;
                } else {
                    conteudoArquivo.append(linha).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo para alteração: " + e.getMessage());
            return false;
        }

        // Sobrescreve o arquivo com as modificações
        if (identificadorEncontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ENTIDADE))) {
                writer.write(conteudoArquivo.toString());
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao salvar alterações no arquivo: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean excluir(long identificador) {
        garantirArquivo();

        boolean identificadorEncontrado = false;
        StringBuilder conteudoArquivo = new StringBuilder();

        // Lê o arquivo e remove a linha com o identificador especificado
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ENTIDADE))) {
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

        // Sobrescreve o arquivo com o conteúdo atualizado, sem a linha removida
        if (identificadorEncontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ENTIDADE))) {
                writer.write(conteudoArquivo.toString());
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao salvar exclusão no arquivo: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    public EntidadeOperadora buscar(long identificador) {
        garantirArquivo();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ENTIDADE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith(identificador + ";")) {
                    String[] partes = linha.split(";");
                    if (partes.length >= 3) {  // Verifica se possui ao menos 3 campos
                        return new EntidadeOperadora(
                            identificador,
                            partes[1],
                            Boolean.parseBoolean(partes[2])
                        );
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar entidade operadora no arquivo: " + e.getMessage());
        }
        return null;
    }
}
