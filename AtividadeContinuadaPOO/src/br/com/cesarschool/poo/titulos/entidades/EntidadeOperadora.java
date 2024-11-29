package br.com.cesarschool.poo.titulos.entidades;

import br.gov.cesarschool.poo.daogenerico.Entidade;

/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo long
 * nome, do tipo String
 * autorizadoAcao, do tipo double
 * saldoAcao, do tipo double
 * saldoTituloDivida, do tipo double
 * 
 * Deve ter um construtor p�blico que inicializa os atributos identificador, nome
 * e autorizadoAcao. Deve ter m�todos set/get p�blicos para os atributos identificador, nome
 * e autorizadoAcao. O atributo identificador � read-only fora da classe.
 * 
 * Os atributos saldoAcao e saldoTituloDivida devem ter apenas m�todos get p�blicos.
 * 
 * Outros m�todos p�blicos:
 * 
 *  void creditarSaldoAcao(double valor): deve adicionar valor ao saldoAcao
 *  void debitarSaldoAcao(double valor): deve diminuir valor de saldoAcao / pode ser negativado?
 *  void creditarSaldoTituloDivida(double valor): deve adicionar valor ao saldoTituloDivida
 *  void debitarSaldoTituloDivida(double valor): deve diminuir valor de saldoTituloDivida  
 */

public class EntidadeOperadora extends Entidade {
	
	private long identificador;
	private String nome;
	private boolean autorizadoAcao;
	private double saldoAcao;
	private double saldoTituloDivida;
	
	public EntidadeOperadora(long identificador, String nome, double autorizadoAcao) {
        super();
        this.identificador = identificador;
        this.nome = nome;
    }
	
	@Override
	public String getIdUnico() {
		return String.valueOf(identificador);
	}
	
	public long getIdentificador() {
		return identificador;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean getAutorizadoAcao() {
		return autorizadoAcao;
	}
	
	public void setAutorizadoAcao(boolean autorizadoAcao) {
		this.autorizadoAcao = autorizadoAcao;
	}
	
	public double getSaldoAcao() {
		return saldoAcao;
	}
	
	public double getSaldoTituloDivida() {
		return saldoTituloDivida;
	}
	
	public void creditarSaldoAcao(double valor) {
		saldoAcao = saldoAcao + valor;
	}
	
	public void debitarSaldoAcao(double valor) {
		saldoAcao = saldoAcao - valor;
	}
	
	public void creditarSaldoTituloDivida(double valor) {
		saldoTituloDivida = saldoTituloDivida + valor;
	}
	
	public void debitarSaldoTituloDivida(double valor) {
		saldoTituloDivida = saldoTituloDivida - valor;
	}
	
	@Override
    public String toString() {
        return String.format(
            "Ação [ID: %d, Nome: %s, Autorização de Ação: %s, Saldo de Ação: %.2f, Saldo de título dívida: %.2f]",
            getIdentificador(), getNome(), getAutorizadoAcao(), getSaldoAcao(), getSaldoTituloDivida()
        );
    }
	
}