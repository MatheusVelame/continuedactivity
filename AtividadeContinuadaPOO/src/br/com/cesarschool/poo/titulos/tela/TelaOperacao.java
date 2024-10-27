package br.com.cesarschool.poo.titulos.tela;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaOperacao extends JFrame {
    private static final long serialVersionUID = 1L;
    private final MediatorOperacao mediatorOperacao = MediatorOperacao.getUnicaInstancia();
    private final TelaMenuPrincipal menuPrincipal;

    public TelaOperacao(TelaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Operação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JButton btnRealizarOperacao = new JButton("Realizar Operação");
        JButton btnGerarExtrato = new JButton("Gerar Extrato");
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");

        
        btnRealizarOperacao.addActionListener(e -> {
			try {
				realizarOperacao();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
        btnGerarExtrato.addActionListener(e -> gerarExtrato());
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

        
        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.add(btnRealizarOperacao);
        painel.add(btnGerarExtrato);
        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);
    }

    private void realizarOperacao() throws IOException {
        try {
            String ehAcao = JOptionPane.showInputDialog(this, "É uma ação? (sim/não)").trim().toLowerCase();
            boolean ehAcaoBoolean = ehAcao.equals("sim");

            int entidadeCredito = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Digite o identificador da entidade de crédito:")
            );

            int idEntidadeDebito = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Digite o identificador da entidade de débito:")
            );

            int idAcaoOuTitulo = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Digite o identificador da ação ou título:")
            );

            double valor = Double.parseDouble(
                    JOptionPane.showInputDialog(this, "Digite o valor:")
            );

            String resultado = mediatorOperacao.realizarOperacao(
                    ehAcaoBoolean, entidadeCredito, idEntidadeDebito, idAcaoOuTitulo, valor
            );

            JOptionPane.showMessageDialog(this, resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Entrada inválida.");
        }
    }

    private void gerarExtrato() {
        try {
            int entidade = Integer.parseInt(
                    JOptionPane.showInputDialog(this, "Digite o identificador da entidade:")
            );

            Transacao[] transacoes = mediatorOperacao.gerarExtrato(entidade);

            if (transacoes.length == 0) {
                JOptionPane.showMessageDialog(this, "Nenhuma transação encontrada para essa entidade.");
            } else {
                StringBuilder extrato = new StringBuilder("Extrato de Transações:\n");
                for (Transacao transacao : transacoes) {
                    extrato.append("-------------------------------------------\n");
                    extrato.append("Entidade de Crédito: ").append(transacao.getEntidadeCredito().getNome()).append("\n");
                    extrato.append("Entidade de Débito: ").append(transacao.getEntidadeDebito().getNome()).append("\n");
                    if (transacao.getAcao() != null) {
                        extrato.append("Ação: ").append(transacao.getAcao().getNome()).append("\n");
                    } else if (transacao.getTituloDivida() != null) {
                        extrato.append("Título de Dívida: ").append(transacao.getTituloDivida().getNome()).append("\n");
                    }
                    extrato.append("Valor da Operação: ").append(transacao.getValorOperacao()).append("\n");
                    extrato.append("Data e Hora da Operação: ").append(transacao.getDataHoraOperacao()).append("\n");
                }
                JOptionPane.showMessageDialog(this, extrato.toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido.");
        }
    }

    private void voltarMenuPrincipal() {
        menuPrincipal.setVisible(true);
        this.dispose();  
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaOperacao(new TelaMenuPrincipal()).setVisible(true));
    }
}
