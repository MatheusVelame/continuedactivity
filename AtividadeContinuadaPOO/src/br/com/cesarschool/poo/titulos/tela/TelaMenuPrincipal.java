package br.com.cesarschool.poo.titulos.tela;

import javax.swing.*;
import java.awt.*;

public class TelaMenuPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;

    public TelaMenuPrincipal() {
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        setTitle("Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JButton btnTituloDivida = new JButton("Gerenciar Títulos de Dívida");
        JButton btnAcao = new JButton("Gerenciar Ações");
        JButton btnOperacao = new JButton("Gerenciar Operações");
        JButton btnEntidadeOperadora = new JButton("Gerenciar Entidade Operadora");
        JButton btnSair = new JButton("Sair");

        
        btnTituloDivida.addActionListener(e -> abrirMenuTituloDivida());
        btnAcao.addActionListener(e -> abrirMenuAcao());
        btnOperacao.addActionListener(e -> abrirMenuOperacao()); 
        btnEntidadeOperadora.addActionListener(e -> abrirMenuEntidadeOperadora());
        btnSair.addActionListener(e -> System.exit(0));

        
        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10)); 
        painel.add(btnTituloDivida);
        painel.add(btnAcao);
        painel.add(btnOperacao); 
        painel.add(btnEntidadeOperadora);
        painel.add(btnSair);

        add(painel, BorderLayout.CENTER);
    }

    private void abrirMenuTituloDivida() {
        new TelaMenuTituloDivida(this).setVisible(true);
        this.dispose();
    }

    private void abrirMenuAcao() {
        new TelaMenuAcao(this).setVisible(true);
        this.dispose();
    }
    
    private void abrirMenuOperacao() {
        new TelaOperacao(this).setVisible(true); 
        this.dispose();
    }
    
    private void abrirMenuEntidadeOperadora() {
    	new TelaMenuEntidadeOperadora(this).setVisible(true);
    	this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaMenuPrincipal().setVisible(true));
    }
}


