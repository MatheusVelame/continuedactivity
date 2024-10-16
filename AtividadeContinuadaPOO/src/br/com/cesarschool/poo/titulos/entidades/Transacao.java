package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *  
 * Deve ter um construtor p�blico que inicializa os atributos.
 * Deve ter m�todos get/set p�blicos para todos os atributos, que 
 * s�o read-only fora da classe.
 * 
 *  
 */ 
public class Transacao {
	
	private EntidadeOperadora entidadeCredito;
	private EntidadeOperadora  entidadeDebito;
	private Acao acao;
	private TituloDivida tituloDivida;
	private double valorOperacao;
	private LocalDateTime dataHoraOperacao;
	
	public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
		this.entidadeCredito = entidadeCredito;
		this.entidadeDebito = entidadeDebito;
		this.acao = acao;
		this.tituloDivida = tituloDivida;
		this.valorOperacao = valorOperacao;
		this.dataHoraOperacao = dataHoraOperacao;
	}
	
	public EntidadeOperadora getEntidadeCredito() {
		return entidadeCredito;
	}
	
	public EntidadeOperadora getEntidadeDebito() {
		return entidadeDebito;
	}
	
	@SuppressWarnings("unused")
	private void setEntidadeDebito(EntidadeOperadora entidadeDebito) {
		this.entidadeDebito = entidadeDebito;
	}
	
	public Acao getAcao() {
		return acao;
	}
	
	@SuppressWarnings("unused")
	private void setAcao(Acao acao) {
		this.acao = acao;
	}
	
	public TituloDivida getTituloDivida() {
		return tituloDivida;
	}
	
	@SuppressWarnings("unused")
	private void setTituloDivida(TituloDivida tituloDivida) {
		this.tituloDivida = tituloDivida;
	}
	
	public double getValorOperacao() {
		return valorOperacao;
	}
	
	@SuppressWarnings("unused")
	private void setValorOperacao(double valorOperacao) {
		this.valorOperacao = valorOperacao;
	}
	
	public LocalDateTime getDataHoraOperacao() {
		return dataHoraOperacao;
	}
	
	@SuppressWarnings("unused")
	private void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
		this.dataHoraOperacao = dataHoraOperacao;
	}

}