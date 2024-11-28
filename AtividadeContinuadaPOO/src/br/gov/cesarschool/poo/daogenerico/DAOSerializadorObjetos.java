package br.gov.cesarschool.poo.daogenerico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 * 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */

public class DAOSerializadorObjetos {
	private String nomeDiretorio;
	
	public DAOSerializadorObjetos(Class<?> tipoEntidade) { // o <?> é um tipo generico que aceita cada classe
		this.nomeDiretorio = "." + File.separator + tipoEntidade.getSimpleName(); // direitorio
	}
	
	public boolean incluir(Entidade entidade) {
	    if (entidade == null) {
	        return false;
	    }

	    File diretorio = new File(nomeDiretorio + File.separator);
	    File file = new File(nomeDiretorio + File.separator + entidade.getIdUnico());

	    if (!diretorio.exists()) {
	        diretorio.mkdir();
	    }

	    if (!file.exists()) {
	        entidade.setDataHoraInclusao(LocalDateTime.now());
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
	            oos.writeObject(entidade); // Grava a entidade
	            return true;
	        } catch (IOException e) {
	            return false;
	        }
	    }
	    return false;
	}


	public boolean alterar(Entidade entidade) {
	    if (entidade == null) {
	        return false;
	    }

	    File file = new File(nomeDiretorio + File.separator + entidade.getIdUnico());

	    if (!file.exists()) {
	        return false;
	    }


	    entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
	        oos.writeObject(entidade); // Grava a entidade atualizada
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}


	
	public boolean excluir(String idUnico) {
		
		if (idUnico == "") {
			return false;
		}
		

		File diretorio = new File(nomeDiretorio + File.separator);
		
		File file = new File(nomeDiretorio + File.separator + idUnico);
		
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		
		if (!file.exists()) {
			return false;
		} else {
			file.delete();
			return true;
		}
		
	}
	
	public Entidade buscar(String idUnico) {
	    if (idUnico == null || idUnico.isEmpty()) {
	        return null;
	    }

	    File file = new File(nomeDiretorio + File.separator + idUnico);

	    if (!file.exists()) {
	        return null;
	    }

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
	        Object objeto = ois.readObject();
	        if (objeto instanceof Entidade) {
	            return (Entidade) objeto;
	        }
	    } catch (IOException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    }

	    return null;
	}


	
	public Entidade[] buscarTodos() {
	    File diretorio = new File(nomeDiretorio + File.separator);

	    if (!diretorio.exists() || !diretorio.isDirectory()) {
	        return new Entidade[0];
	    }

	    File[] arquivos = diretorio.listFiles();
	    if (arquivos == null || arquivos.length == 0) {
	        return new Entidade[0];
	    }

	    List<Entidade> entidades = new ArrayList<>();
	    for (File arquivo : arquivos) {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
	            Object objeto = ois.readObject();
	            if (objeto instanceof Entidade) {
	                entidades.add((Entidade) objeto);
	            }
	        } catch (IOException | ClassNotFoundException e) {
	        	e.printStackTrace();
	        }
	    }

	    return entidades.toArray(new Entidade[0]);
	}
}