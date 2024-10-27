package br.com.cesarschool.poo.titulos.tela;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaMenuTituloDivida extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MediatorTituloDivida mediatorTitulo = MediatorTituloDivida.getInstancia();
    private final TelaMenuPrincipal menuPrincipal;

    public TelaMenuTituloDivida(TelaMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setTitle("Gerenciar Título de Dívida");
        setSize(400, 300);
        setLocationRelativeTo(null); 

        
        JButton btnIncluir = new JButton("Incluir Título");
        JButton btnAlterar = new JButton("Alterar Título");
        JButton btnExcluir = new JButton("Excluir Título");
        JButton btnBuscar = new JButton("Buscar Título");
        JButton btnVoltar = new JButton("Voltar");

        
        btnIncluir.addActionListener(e -> {
			try {
				incluirTitulo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        btnAlterar.addActionListener(e -> {
			try {
				alterarTitulo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        btnExcluir.addActionListener(e -> {
			try {
				excluirTitulo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        btnBuscar.addActionListener(e -> {
			try {
				buscarTitulo();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
        btnVoltar.addActionListener(e -> voltarMenuPrincipal());

       
        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.add(btnIncluir);
        painel.add(btnAlterar);
        painel.add(btnExcluir);
        painel.add(btnBuscar);
        painel.add(btnVoltar);

        add(painel, BorderLayout.CENTER);
    }

    private TituloDivida lerTitulo() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador:"));
            String nome = JOptionPane.showInputDialog(this, "Informe o Nome:");
            LocalDate dataValidade = LocalDate.parse(JOptionPane.showInputDialog(this, "Informe a Data de Validade (YYYY-MM-DD):"));
            double taxaJuros = Double.parseDouble(JOptionPane.showInputDialog(this, "Informe a Taxa de Juros:"));

            return new TituloDivida(id, nome, dataValidade, taxaJuros);
        } catch (NumberFormatException | DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Erro: Dados inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void incluirTitulo() throws Exception {
        TituloDivida titulo = lerTitulo();
        if (titulo != null) {
            String erro = mediatorTitulo.incluir(titulo);
            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Título incluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void alterarTitulo() throws Exception {
        TituloDivida titulo = lerTitulo();
        if (titulo != null) {
            String erro = mediatorTitulo.alterar(titulo);
            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Título alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void excluirTitulo() throws Exception {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador do Título:"));
            String erro = mediatorTitulo.excluir(id);
            if (erro == null) {
                JOptionPane.showMessageDialog(this, "Título excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: Identificador inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarTitulo() throws Exception {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Informe o Identificador do Título:"));
            TituloDivida titulo = mediatorTitulo.buscar(id);
            if (titulo != null) {
                JOptionPane.showMessageDialog(this, "Título encontrado: " + titulo);
            } else {
                JOptionPane.showMessageDialog(this, "Título não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
