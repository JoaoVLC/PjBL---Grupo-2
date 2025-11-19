package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.service.BibliotecaService;

public class TelaCadastrarAutor extends JFrame {

    public TelaCadastrarAutor(BibliotecaService service) {

        setTitle("Cadastrar Autor");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 245, 245));
        setContentPane(painel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Cadastrar Autor");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblSobrenome = new JLabel("Sobrenome:");
        JLabel lblNac = new JLabel("Nacionalidade:");

        JTextField txtNome = new JTextField(18);
        JTextField txtSobrenome = new JTextField(18);
        JTextField txtNac = new JTextField(18);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(30, 144, 255));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalvar.setFocusPainted(false);

        // ===== Layout =====

        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        painel.add(titulo, c);

        c.gridwidth = 1;
        c.gridy++;

        c.gridx = 0; painel.add(lblNome, c);
        c.gridx = 1; painel.add(txtNome, c);

        c.gridy++;
        c.gridx = 0; painel.add(lblSobrenome, c);
        c.gridx = 1; painel.add(txtSobrenome, c);

        c.gridy++;
        c.gridx = 0; painel.add(lblNac, c);
        c.gridx = 1; painel.add(txtNac, c);

        c.gridy++;
        c.gridx = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        painel.add(btnSalvar, c);

        // === Ação do botão ===
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String sobrenome = txtSobrenome.getText();
            String nac = txtNac.getText();

            if (nome.isBlank() || sobrenome.isBlank() || nac.isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            service.cadastrarAutor(nome, sobrenome, nac);
            JOptionPane.showMessageDialog(this, "Autor cadastrado com sucesso!");

            dispose();
        });

        setVisible(true);
    }
}
