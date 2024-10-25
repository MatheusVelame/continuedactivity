package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 	           
 *   002192 ; BCB ; true ; 0.00 ; 1890220034.0 ; 001112 ; BOFA ; true ; 12900000210.00 ; 3564234127.0 ; 1 ; PETROBRAS ; 2024-12-12 ; 30.33 ; null ; 100000.0 ; 2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transa��es cuja entidadeCredito tenha identificador igual ao
 * recebido como par�metro.  
 */

public class RepositorioTransacao {
	public void incluir(Transacao transacao) {
		
		EntidadeOperadora objetosEntidadeCredito = transacao.getEntidadeCredito();
		EntidadeOperadora objetosEntidadeDebito = transacao.getEntidadeDebito();
		Acao objetosAcao = transacao.getAcao();
		TituloDivida objetosTituloDivida = transacao.getTituloDivida();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transacao.txt", true))) {
	        writer.write(objetosEntidadeCredito.getIdentificador() + ";" + objetosEntidadeCredito.getNome() + ";" + objetosEntidadeCredito.getAutorizadoAcao() + ";" + objetosEntidadeCredito.getSaldoAcao() + ";" + objetosEntidadeCredito.getSaldoTituloDivida() + ";");
	        writer.write(objetosEntidadeDebito.getIdentificador() + ";" + objetosEntidadeDebito.getNome() + ";" + objetosEntidadeDebito.getAutorizadoAcao() + ";" + objetosEntidadeDebito.getSaldoAcao() + ";" + objetosEntidadeDebito.getSaldoTituloDivida() + ";");
	        if (objetosAcao != null) {
	            writer.write(objetosAcao.getIdentificador() + ";" + objetosAcao.getNome() + ";" + objetosAcao.getDataDeValidade() + ";" + objetosAcao.getValorUnitario() + ";");
	        } else {
	            writer.write("null;");
	        }

	        if (objetosTituloDivida != null) {
	            writer.write(objetosTituloDivida.getIdentificador() + ";" + objetosTituloDivida.getNome() + ";" + objetosTituloDivida.getDataDeValidade() + ";" + objetosTituloDivida.getTaxaJuros() + ";");
	        } else {
	            writer.write("null;");
	        }
	        writer.write(transacao.getValorOperacao() + ";" + transacao.getDataHoraOperacao());        
	        writer.newLine();     
	    } catch (IOException e) {
	        e.printStackTrace();
	    }	
		
	}
	
	
	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
	    List<Transacao> transacoesEncontradas = new ArrayList<>();
	    boolean encontrarIdentificadorFlag = false;
	    
	    try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))){
	        String linha;
	        while ((linha = reader.readLine()) != null) {
	            String[] objetosLinha = linha.split(";");
	            if (Long.parseLong(objetosLinha[0].trim()) == identificadorEntidadeCredito) {
	            	encontrarIdentificadorFlag = true;
	                EntidadeOperadora objetosCredito = new EntidadeOperadora(Long.parseLong(objetosLinha[0].trim()), objetosLinha[1].trim(), Boolean.parseBoolean(objetosLinha[2].trim()));
	                EntidadeOperadora objetosDebito = new EntidadeOperadora(Long.parseLong(objetosLinha[5].trim()), objetosLinha[6].trim(), Boolean.parseBoolean(objetosLinha[7].trim()));
	                
	                Acao acao = null;
	                TituloDivida tituloDivida = null;
	                
	                if (!objetosLinha[10].equals("null")) {
	                    acao = new Acao(Integer.parseInt(objetosLinha[10].trim()), objetosLinha[11].trim(), LocalDate.parse(objetosLinha[12].trim()), Double.parseDouble(objetosLinha[13].trim()));
	                }
	                
	                if (!objetosLinha[14].equals("null")) {
	                    tituloDivida = new TituloDivida(Integer.parseInt(objetosLinha[14].trim()), objetosLinha[15].trim(), LocalDate.parse(objetosLinha[16].trim()), Double.parseDouble(objetosLinha[17].trim()));
	                }
	                
	                double valorOperacao = Double.parseDouble(objetosLinha[18].trim());
	                LocalDateTime dataHoraOperacao = LocalDateTime.parse(objetosLinha[19].trim());
	                
	                Transacao transacao = new Transacao(objetosCredito, objetosDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
	                transacoesEncontradas.add(transacao);
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }
	    
	    if(encontrarIdentificadorFlag) {
	    	return transacoesEncontradas.toArray(new Transacao[0]);
	    } else {
	    	return null;
	    }
	}
	
	public Transacao[] buscarPorEntidadeDevedora(long identificadorEntidadeDebito) {
		List<Transacao> transacoesEncontradas = new ArrayList<>();
		boolean encontrarIdentificadorFlag = false;
		
		try (BufferedReader reader = new BufferedReader(new FileReader("Transacao.txt"))){
	        String linha;
	        while ((linha = reader.readLine()) != null) {
	            String[] objetosLinha = linha.split(";");
	            if (Long.parseLong(objetosLinha[0].trim()) == identificadorEntidadeDebito) {
	            	encontrarIdentificadorFlag = true;
	                EntidadeOperadora objetosCredito = new EntidadeOperadora(Long.parseLong(objetosLinha[0].trim()), objetosLinha[1].trim(), Boolean.parseBoolean(objetosLinha[2].trim()));
	                EntidadeOperadora objetosDebito = new EntidadeOperadora(Long.parseLong(objetosLinha[5].trim()), objetosLinha[6].trim(), Boolean.parseBoolean(objetosLinha[7].trim()));
	                
	                Acao acao = null;
	                TituloDivida tituloDivida = null;
	                
	                if (!objetosLinha[10].equals("null")) {
	                    acao = new Acao(Integer.parseInt(objetosLinha[10].trim()), objetosLinha[11].trim(), LocalDate.parse(objetosLinha[12].trim()), Double.parseDouble(objetosLinha[13].trim()));
	                }
	                
	                if (!objetosLinha[14].equals("null")) {
	                    tituloDivida = new TituloDivida(Integer.parseInt(objetosLinha[14].trim()), objetosLinha[15].trim(), LocalDate.parse(objetosLinha[16].trim()), Double.parseDouble(objetosLinha[17].trim()));
	                }
	                
	                double valorOperacao = Double.parseDouble(objetosLinha[18].trim());
	                LocalDateTime dataHoraOperacao = LocalDateTime.parse(objetosLinha[19].trim());
	                
	                Transacao transacao = new Transacao(objetosCredito, objetosDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
	                transacoesEncontradas.add(transacao);
	            }
	        }
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	    }
		
		if(encontrarIdentificadorFlag) {
	    	return transacoesEncontradas.toArray(new Transacao[0]);
	    } else {
	    	return null;
	    }
	}
}