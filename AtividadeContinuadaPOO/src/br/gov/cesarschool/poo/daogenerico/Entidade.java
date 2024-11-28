package br.gov.cesarschool.poo.daogenerico;

import java.time.LocalDateTime;
import java.io.Serializable;

/*
 * Esta classe representa uma superclasse de todas as entidades.  OK
 * 
 * Deve ter os seguintes atributos (com respectivos set e get): OK
 *	LocalDateTime dataHoraInclusao, 
 *  LocalDateTime dataHoraUltimaAlteracao,
 *	String usuarioInclusao e 
 *	String usuarioUltimaAlteracao
 *
 * Deve ter um único construtor sem parâmetros. OK
 * 
 * Deve ser abstrata. OK
 * 
 * Deve ter um método abstrato getIdUnico(). OK 
 * 
 * Deve implementar a interface Serializable do JAVA OK
 */

public abstract class Entidade implements Serializable {
	
	private LocalDateTime dataHoraInclusao;
	private LocalDateTime dataHoraUltimaAlteracao;
	private String usuarioInclusao;
	private String usuarioUltimaAlteracao;
	
	public abstract String getIdUnico();
	
	public Entidade() {
		
	}
	
	
	public LocalDateTime getDataHoraInclusao() {
		return dataHoraInclusao;
	}
	
	public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}
	
	public LocalDateTime getDataHoraUltimaAlteracao() {
		return dataHoraUltimaAlteracao;
	}
	
	public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
		this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
	}
	
	public String getUsuarioInclusao() {
		return usuarioInclusao;
	}
	
	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}
	
	public String getUsuarioUltimaAlteracao() {
		return usuarioUltimaAlteracao;
	}
	
	public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
		this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
	}
	
}