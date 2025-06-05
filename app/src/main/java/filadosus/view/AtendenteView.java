package filadosus.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import filadosus.controller.FilaController;
import filadosus.model.Cliente;

public class AtendenteView extends JFrame {
    private FilaController controller;
    private JTextArea displayArea;

    public AtendenteView(FilaController controller) {
        this.controller = controller;
        setTitle("Painel do Atendente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel botoesPanel = new JPanel(new GridLayout(1, 2));
        JButton btnChamarProximo = new JButton("Chamar Próximo");
        JButton btnPausar = new JButton("Pausar");

        btnChamarProximo.addActionListener(e -> chamarProximo());
        btnPausar.addActionListener(e -> pausar());

        botoesPanel.add(btnChamarProximo);
        botoesPanel.add(btnPausar);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(botoesPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void chamarProximo() {
        Cliente cliente = controller.chamarProximo();
        if (cliente != null) {
            displayArea.append("Chamando cliente: " + cliente.getSenha() + 
                               " (" + cliente.getClasse().getDescricao() + ")\n");
        } else {
            displayArea.append("Nenhum cliente na fila.\n");
        }
    }

    private void pausar() {
        displayArea.append("Atendimento pausado.\n");
    }
}
