package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.model.Usuario;
import main.service.BibliotecaService;

public class TelaMulta extends JFrame {

    public TelaMulta(BibliotecaService service) {

        setTitle("Pagamento de Multas");
        setSize(420, 230);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 245, 245));
        setContentPane(painel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Pagamento de Multas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel l1 = new JLabel("ID do Usuário:");
        JTextField t1 = new JTextField(18);

        JButton pagar = new JButton("Quitar Multas");
        pagar.setBackground(new Color(30, 144, 255));
        pagar.setForeground(Color.WHITE);
        pagar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pagar.setFocusPainted(false);

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(titulo, c);

        c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;
        c.gridy++; c.gridx = 0; painel.add(l1, c);
        c.gridx = 1; painel.add(t1, c);

        c.gridy++; c.gridx = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(pagar, c);

        pagar.addActionListener(e -> {

            Usuario u = service.buscarUsuarioPorId(t1.getText());

            if (u == null) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
                return;
            }

            double total = service.quitarMultasUsuario(u);

            JOptionPane.showMessageDialog(this,
                    "Multas quitadas!\nTotal pago: R$ " + total);

            dispose();
        });

        setVisible(true);
    }
}
