package br.com.cesarschool.poo.titulos.tela;

import javax.swing.*;
import java.awt.*;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaMenuEntidadeOperadora extends JFrame {

    private static final long serialVersionUID = 1L;
    private final MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getUnicaInstancia();
    private final TelaMenuPrincipal menuPrincipal;

    public TelaMenuEntidadeOperadora(TelaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Entidade Operadora");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton btnIncluir = new JButton("Incluir Entidade");
        JButton btnAlterar = new JButton("Alterar Entidade");
        JButton btnExcluir = new JButton("Excluir Entidade");
        JButton btnBuscar = new JButton("Buscar Entidade");
        JButton btnVoltar = new JButton("Voltar");

        btnIncluir.addActionListener(e -> incluirEntidade());
        btnAlterar.addActionListener(e -> alterarEntidade());
        btnExcluir.addActionListener(e -> excluirEntidade());
        btnBuscar.addActionListener(e -> buscarEntidade());
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.add(btnIncluir);
        painel.add(btnAlterar);
        painel.add(btnExcluir);
        painel.add(btnBuscar);
        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);
    }

    private void incluirEntidade() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador:"));
            String nome = JOptionPane.showInputDialog(this, "Informe o Nome:");
            int opcaoAcao = JOptionPane.showConfirmDialog(this, "É uma ação?", "Tipo de Entidade", JOptionPane.YES_NO_OPTION);
            boolean ehAcao = opcaoAcao == JOptionPane.YES_OPTION;

            EntidadeOperadora entidade = new EntidadeOperadora(id, nome, ehAcao);
            String erro = mediatorEntidadeOperadora.incluir(entidade);

            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Entidade incluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarEntidade() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador:"));
            String nome = JOptionPane.showInputDialog(this, "Informe o Nome:");
            int opcaoAcao = JOptionPane.showConfirmDialog(this, "É uma ação?", "Tipo de Entidade", JOptionPane.YES_NO_OPTION);
            boolean ehAcao = opcaoAcao == JOptionPane.YES_OPTION;

            EntidadeOperadora entidade = new EntidadeOperadora(id, nome, ehAcao);
            String erro = mediatorEntidadeOperadora.alterar(entidade);

            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Entidade alterada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirEntidade() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador da Entidade:"));
            String erro = mediatorEntidadeOperadora.excluir(id);

            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Entidade excluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarEntidade() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador da Entidade:"));
            EntidadeOperadora entidade = mediatorEntidadeOperadora.buscar(id);

            if (entidade != null) {
                JOptionPane.showMessageDialog(this, "Entidade encontrada: " + entidade);
            } else {
                JOptionPane.showMessageDialog(this, "Entidade não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarMenuPrincipal() {
        setVisible(false);
        menuPrincipal.setVisible(true);
    }
}
