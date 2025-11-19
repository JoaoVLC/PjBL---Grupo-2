package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.model.Usuario;
import main.service.BibliotecaService;

public class TelaCadastrarUsuario extends JFrame {

    public TelaCadastrarUsuario(BibliotecaService service) {

        setTitle("Cadastrar Usuário");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 245, 245));
        setContentPane(painel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Cadastrar Usuário");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblId   = new JLabel("ID:");
        JLabel lblTipo = new JLabel("Tipo:");

        JTextField txtNome = new JTextField(18);
        JTextField txtId = new JTextField(18);

        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"aluno", "professor"});

        JButton salvar = new JButton("Salvar");
        salvar.setBackground(new Color(30, 144, 255));
        salvar.setForeground(Color.WHITE);
        salvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salvar.setFocusPainted(false);

        // Layout
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(titulo, c);
        c.gridwidth = 1; c.anchor = GridBagConstraints.WEST;

        c.gridy++; c.gridx = 0; painel.add(lblNome, c);
        c.gridx = 1; painel.add(txtNome, c);

        c.gridy++; c.gridx = 0; painel.add(lblId, c);
        c.gridx = 1; painel.add(txtId, c);

        c.gridy++; c.gridx = 0; painel.add(lblTipo, c);
        c.gridx = 1; painel.add(cbTipo, c);

        c.gridy++; c.gridx = 0; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        painel.add(salvar, c);

        // Ação do botão
        salvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String id = txtId.getText();
            String tipo = cbTipo.getSelectedItem().toString();

            Usuario u = service.cadastrarUsuario(nome, id, tipo);

            if (u != null)
                JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            else
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar.");

            dispose();
        });

        setVisible(true);
    }
}
