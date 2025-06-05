package filadosus.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import filadosus.controller.FilaController;
import filadosus.model.Cliente;

public class ClienteView extends JFrame {
    private FilaController controller;
    private JTextArea filaText;

    public ClienteView(FilaController controller) {
        this.controller = controller;
        setTitle("Atendimento - Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JButton btnPreferencial = new JButton("Preferencial");
        btnPreferencial.addActionListener(e -> gerarSenha("A"));

        JButton btnComum = new JButton("Comum");
        btnComum.addActionListener(e -> gerarSenha("B"));

        filaText = new JTextArea();
        filaText.setEditable(false);

        panel.add(btnPreferencial);
        panel.add(btnComum);
        panel.add(new JScrollPane(filaText));

        add(panel);
        setVisible(true);
    }

    private void gerarSenha(String codigo) {
        Cliente cliente = controller.gerarSenha(codigo);
        filaText.append("Senha gerada: " + cliente.getSenha() + "\n");
    }
}
