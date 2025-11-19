package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.model.Livro;
import main.service.BibliotecaService;

public class TelaCadastrarLivro extends JFrame {

    public TelaCadastrarLivro(BibliotecaService service) {


        setTitle("Cadastrar Livro");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        painel.setBackground(new Color(245, 245, 245));
        setContentPane(painel);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Cadastrar Livro");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel l1 = new JLabel("Título:");
        JLabel l2 = new JLabel("ISBN:");
        JLabel l3 = new JLabel("ID do Autor:");
        JLabel l4 = new JLabel("Tipo:");

        JTextField t1 = new JTextField(20);  // título
        JTextField t2 = new JTextField(20);  // isbn
        JTextField t3 = new JTextField(20);  // id autor

        JComboBox<String> tipos = new JComboBox<>(new String[]{"fisico", "digital"});

        JButton salvar = new JButton("Salvar");
        salvar.setBackground(new Color(30, 144, 255));
        salvar.setForeground(Color.WHITE);
        salvar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        salvar.setFocusPainted(false);

        // ===== Título =====
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        painel.add(titulo, c);

        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;

        // ===== Campo Título =====
        c.gridy++; c.gridx = 0; painel.add(l1, c);
        c.gridx = 1; painel.add(t1, c);

        // ===== Campo ISBN =====
        c.gridy++; c.gridx = 0; painel.add(l2, c);
        c.gridx = 1; painel.add(t2, c);

        // ===== Campo ID Autor =====
        c.gridy++; c.gridx = 0; painel.add(l3, c);
        c.gridx = 1; painel.add(t3, c);

        // ===== Combo Tipo =====
        c.gridy++; c.gridx = 0; painel.add(l4, c);
        c.gridx = 1; painel.add(tipos, c);

        // ===== Botão =====
        c.gridy++; c.gridx = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        painel.add(salvar, c);

        salvar.addActionListener(e -> {
            try {
                String tituloLivro = t1.getText();
                String isbn = t2.getText();
                int idAutor = Integer.parseInt(t3.getText());
                String tipo = tipos.getSelectedItem().toString();

                Livro livro = service.cadastrarLivro(tituloLivro, idAutor, isbn, tipo);

                if (livro == null) {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro.");
                } else {
                    JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                }

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: Verifique os campos.");
            }
        });

        setVisible(true);
    }
}

