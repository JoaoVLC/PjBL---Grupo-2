package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.model.Livro;
import main.model.Usuario;
import main.service.BibliotecaService;

public class TelaDevolucao extends JFrame {

    public TelaDevolucao(BibliotecaService service) {

        setTitle("Registrar Devolução");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 245, 245));
        setContentPane(painel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Registrar Devolução");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel l1 = new JLabel("ID do Usuário:");
        JLabel l2 = new JLabel("ISBN do Livro:");

        JTextField t1 = new JTextField(18);
        JTextField t2 = new JTextField(18);

        JButton confirmar = new JButton("Registrar");
        confirmar.setBackground(new Color(30, 144, 255));
        confirmar.setForeground(Color.WHITE);
        confirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmar.setFocusPainted(false);

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(titulo, c);

        c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;
        c.gridy++; c.gridx = 0; painel.add(l1, c);
        c.gridx = 1; painel.add(t1, c);

        c.gridy++; c.gridx = 0; painel.add(l2, c);
        c.gridx = 1; painel.add(t2, c);

        c.gridy++; c.gridx = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(confirmar, c);

        confirmar.addActionListener(e -> {

            Usuario u = service.buscarUsuarioPorId(t1.getText());
            Livro l = service.buscarLivroPorISBN(t2.getText());

            if (u == null || l == null) {
                JOptionPane.showMessageDialog(this, "Usuário ou livro não encontrado!");
                return;
            }

            service.registrarDevolucao(u, l);
            JOptionPane.showMessageDialog(this,
                    "Devolução registrada!\nMulta atual: R$ " + u.getMulta());

            dispose();
        });

        setVisible(true);
    }
}
