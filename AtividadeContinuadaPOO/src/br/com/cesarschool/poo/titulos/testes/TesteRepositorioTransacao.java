package br.com.cesarschool.poo.titulos.testes;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioGeral;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public class TesteRepositorioTransacao extends TesteGeral {
	private static final RepositorioTransacao DAO = new RepositorioTransacao();
	private static final String NOME_DIR_TRANSACAO = PONTO + SEP_ARQUIVO + Transacao.class.getSimpleName();
	
	@Test
	public void testDAO00() {
		Assertions.assertTrue(DAO instanceof RepositorioGeral);
		DAOSerializadorObjetos dao = DAO.getDao();
		Assertions.assertNotNull(dao);
	}
	@Test
	public void testDAO01() {
		excluirArquivosDiretorio(NOME_DIR_TRANSACAO);
		Acao acao = new Acao(1, "A1", LocalDate.now(), 100.0);
		EntidadeOperadora eaC = new EntidadeOperadora(2, "EO1", 100.0);
		EntidadeOperadora eaD = new EntidadeOperadora(3, "EO2", 101.0);
		Transacao tr = new Transacao(eaC, eaD, acao, null, 10.0, LocalDateTime.now());
		DAO.incluir(tr);		
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_TRANSACAO), 1); 
		Assertions.assertTrue(new File(obterNomeArquivo(NOME_DIR_TRANSACAO, tr)).exists());
	}
	@Test
	public void testDAO02() {
		excluirArquivosDiretorio(NOME_DIR_TRANSACAO);
		TituloDivida titulo = new TituloDivida(4, "T4", LocalDate.now(), 102.0);
		EntidadeOperadora eaC = new EntidadeOperadora(5, "EO5", 103.0);
		EntidadeOperadora eaD = new EntidadeOperadora(6, "EO6", 104.0);
		Transacao tr = new Transacao(eaC, eaD, null, titulo, 11.0, LocalDateTime.now());
		DAO.incluir(tr);		
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_TRANSACAO), 1); 
		Assertions.assertTrue(new File(obterNomeArquivo(NOME_DIR_TRANSACAO, tr)).exists());
	}
	@Test
	public void testDAO03() {
		excluirArquivosDiretorio(NOME_DIR_TRANSACAO);
		long idCred = 7;
		TituloDivida titulo = new TituloDivida(5, "T5", LocalDate.now(), 107.0);
		EntidadeOperadora ea1 = new EntidadeOperadora(idCred, "EO7", 105.0);
		EntidadeOperadora ea2 = new EntidadeOperadora(8, "EO8", 106.0);
		Transacao tr1 = new Transacao(ea1, ea2, null, titulo, 12.0, LocalDateTime.now());
		Transacao tr2 = new Transacao(ea2, ea1, null, titulo, 13.0, LocalDateTime.now().plusDays(1));
		Transacao tr3 = new Transacao(ea2, ea1, null, titulo, 14.0, LocalDateTime.now().plusDays(2));
		Transacao tr4 = new Transacao(ea1, ea2, null, titulo, 15.0, LocalDateTime.now().plusDays(3));
		DAO.incluir(tr1);		
		DAO.incluir(tr2);
		DAO.incluir(tr3);
		DAO.incluir(tr4);
		Transacao[] trs = DAO.buscarPorEntidadeCredora(idCred);
		Assertions.assertNotNull(trs);
		Assertions.assertEquals(trs.length, 2);
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(tr1, trs[0]));	
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(tr2, trs[1]));
	}
	@Test
	public void testDAO04() {
		excluirArquivosDiretorio(NOME_DIR_TRANSACAO);
		long idDeb = 9;
		TituloDivida titulo = new TituloDivida(6, "T6", LocalDate.now(), 108.0);
		EntidadeOperadora ea1 = new EntidadeOperadora(8, "EO7", 109.0);
		EntidadeOperadora ea2 = new EntidadeOperadora(idDeb, "EO8", 110.0);
		Transacao tr1 = new Transacao(ea1, ea2, null, titulo, 12.0, LocalDateTime.now());
		Transacao tr2 = new Transacao(ea1, ea2, null, titulo, 13.0, LocalDateTime.now().plusDays(1));
		Transacao tr3 = new Transacao(ea1, ea2, null, titulo, 14.0, LocalDateTime.now().plusDays(2));
		Transacao tr4 = new Transacao(ea1, ea2, null, titulo, 15.0, LocalDateTime.now().plusDays(3));
		DAO.incluir(tr1);		
		DAO.incluir(tr2);
		DAO.incluir(tr3);
		DAO.incluir(tr4);
		Transacao[] trs = DAO.buscarPorEntidadeCredora(idDeb);
		Assertions.assertNotNull(trs);
		Assertions.assertEquals(trs.length, 0);
	}
}