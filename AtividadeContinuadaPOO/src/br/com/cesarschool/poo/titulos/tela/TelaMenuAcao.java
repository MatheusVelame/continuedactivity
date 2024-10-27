package br.com.cesarschool.poo.titulos.tela;

import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;
import br.com.cesarschool.poo.titulos.entidades.Acao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TelaMenuAcao extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final MediatorAcao mediatorAcao = MediatorAcao.getInstance();
    private final TelaMenuPrincipal menuPrincipal;

    public TelaMenuAcao(TelaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Ações");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnIncluir = new JButton("Incluir Ação");
        JButton btnAlterar = new JButton("Alterar Ação");
        JButton btnExcluir = new JButton("Excluir Ação");
        JButton btnBuscar = new JButton("Buscar Ação");
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");

        btnIncluir.addActionListener(e -> incluirAcao());
        btnAlterar.addActionListener(e -> alterarAcao());
        btnExcluir.addActionListener(e -> excluirAcao());
        btnBuscar.addActionListener(e -> buscarAcao());
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.add(btnIncluir);
        painel.add(btnAlterar);
        painel.add(btnExcluir);
        painel.add(btnBuscar);
        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);
    }

    private void voltarMenuPrincipal() {
        menuPrincipal.setVisible(true);
        this.dispose();
    }


    private void incluirAcao() {
        Acao acao = lerAcao();
        if (acao == null) {
            JOptionPane.showMessageDialog(this, "Erro: Não foi possível ler os dados da ação.");
            return;
        }
        String erro = mediatorAcao.incluir(acao);
        if (erro == null) {
            JOptionPane.showMessageDialog(this, "Ação incluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, erro);
        }
    }

    private void alterarAcao() {
        Acao acao = lerAcao();
        String erro = mediatorAcao.alterar(acao);
        if (erro == null) {
            JOptionPane.showMessageDialog(this, "Ação alterada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, erro);
        }
    }

    private Acao lerAcao() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Identificador:"));
            String nome = JOptionPane.showInputDialog(this, "Nome:");
            String dataValidadeStr = JOptionPane.showInputDialog(this, "Data de Validade (YYYY-MM-DD):");
            LocalDate dataValidade = LocalDate.parse(dataValidadeStr, DateTimeFormatter.ISO_LOCAL_DATE);
            double valorUnitario = Double.parseDouble(JOptionPane.showInputDialog(this, "Valor Unitário:"));
            return new Acao(id, nome, dataValidade, valorUnitario);
        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Erro: Entrada inválida.");
            return null;
        }
    }

    private void excluirAcao() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o identificador da ação:"));
            String erro = mediatorAcao.excluir(id);
            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Ação excluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido.");
        }
    }

    private void buscarAcao() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o identificador da ação:"));
            Acao acao = mediatorAcao.buscar(id);
            if (acao != null) {
                JOptionPane.showMessageDialog(this, "Ação encontrada: " + acao);
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Ação não encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaMenuAcao(null).setVisible(true));
    }
}
