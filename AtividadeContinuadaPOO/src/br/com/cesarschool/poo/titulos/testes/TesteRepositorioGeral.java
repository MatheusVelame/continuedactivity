package br.com.cesarschool.poo.titulos.testes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioGeral;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public class TesteRepositorioGeral extends TesteGeral {
	@Test
	public void testDAO00() {
		RepositorioGeral dao = new RepositorioAcao();
		DAOSerializadorObjetos dso = dao.getDao();
		Assertions.assertNotNull(dso);
	}
	@Test
	public void testDAO01() {
		Class<?> c = RepositorioGeral.class;
		Assertions.assertEquals(c.getModifiers(), 1025);		
		try {
			Method m = c.getMethod("getClasseEntidade", null);
			Assertions.assertEquals(m.getModifiers(), 1025);		
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail();
		}
	}
	@Test
	public void testDAO02() {
		RepositorioGeral dao = new RepositorioAcao();
		Assertions.assertEquals(dao.getClasseEntidade(), Acao.class);		
	}
	@Test
	public void testDAO03() {
		RepositorioGeral dao = new RepositorioTituloDivida();
		Assertions.assertEquals(dao.getClasseEntidade(), TituloDivida.class);
		
	}	
	@Test
	public void testDAO04() {
		RepositorioGeral dao = new RepositorioEntidadeOperadora();
		Assertions.assertEquals(dao.getClasseEntidade(), EntidadeOperadora.class);
		
	}	
	@Test
	public void testDAO05() {
		RepositorioGeral dao = new RepositorioTransacao();
		Assertions.assertEquals(dao.getClasseEntidade(), Transacao.class);
		
	}	
}
TesteRepositorioGeral.java
Displaying TesteRepositorioGeral.java.
DAOSerializadorObjetos nos repositórios
EDUARDO GONÇALVES CALABRIA
•
Nov 14 (Edited Nov 21)
Caros, 

As classes de testes vão dizer a vocês por si só o que e como vocês têm que fazer o "embarque" do DAOSerializadorObjetos nos repositórios. A ideia é substituir a implementação atual dos repositórios por outra que, de alguma forma (olhem os testes) use a classe DAOSerializadorObjetos. 

As classes disponbilizadas devem ser incorporadas ao projeto de vocês. Substituam as classes já existentes pelas versões disponibilizadas aqui neste post.

ComparadoraObjetosSerial.java
Java

TesteGeral.java
Java

TesteRepositorioAcao.java
Java

TesteRepositorioEntidadeOperadora.java
Java

TesteRepositorioGeral.java
Java

TesteRepositorioTituloDivida.java
Java

TesteRepositorioTransacao.java
Java

TestesDAOSerializador.java
Java
Class comments
