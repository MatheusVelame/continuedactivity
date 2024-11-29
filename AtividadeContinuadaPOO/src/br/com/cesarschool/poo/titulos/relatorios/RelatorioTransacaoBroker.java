package br.com.cesarschool.poo.titulos.relatorios;

import java.util.ArrayList;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.utils.ComparadorTransacaoPorNomeCredora;
import br.com.cesarschool.poo.titulos.utils.Ordenador;
import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;

public class RelatorioTransacaoBroker {

	public Transacao[] relatorioTransacaoPorNomeEntidadeCredora() {
		ComparadorTransacaoPorNomeCredora comparadorTransacaoPorNomeCredora = new ComparadorTransacaoPorNomeCredora();
		DAOSerializadorObjetos dao = new DAOSerializadorObjetos(Transacao.class);
        Entidade[] entidades = dao.buscarTodos();
        
        ArrayList<Transacao> arrayTransacao = new ArrayList<>(); 

        for (Entidade entidade : entidades) {
            if (entidade instanceof Transacao) {
                arrayTransacao.add((Transacao) entidade);
            }
        }

        Transacao [] transacoesOrdenadas = arrayTransacao.toArray(new Transacao [0]);
        
        Ordenador.ordenar(transacoesOrdenadas, comparadorTransacaoPorNomeCredora);
        
        return transacoesOrdenadas;
        
	}
	
	public Transacao[] relatorioTransacaoPorDataHora() {
		DAOSerializadorObjetos dao = new DAOSerializadorObjetos(Transacao.class);
        Entidade[] entidades = dao.buscarTodos();
        
        ArrayList<Transacao> arrayTransacao = new ArrayList<>(); 

        for (Entidade entidade : entidades) {
            if (entidade instanceof Transacao) {
                arrayTransacao.add((Transacao) entidade);
            }
        }

        Transacao [] transacoesOrdenadas = arrayTransacao.toArray(new Transacao [0]);
        
        Ordenador.ordenar(transacoesOrdenadas);
        
        return transacoesOrdenadas;
	}
	
}
