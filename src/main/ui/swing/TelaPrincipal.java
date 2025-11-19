package main.ui.swing;

import javax.swing.*;
import java.awt.*;
import main.service.BibliotecaService;

public class TelaPrincipal extends JFrame {

    private BibliotecaService service;

    public TelaPrincipal(BibliotecaService service) {
        this.service = service;

        setTitle("Sistema de Biblioteca");
        setSize(450, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // ====== TÍTULO ======
        JLabel titulo = new JLabel("Sistema de Biblioteca", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // ====== PAINEL DE BOTÕES ======
        JPanel painel = new JPanel(new GridLayout(6, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 40, 30, 40));
        painel.setBackground(new Color(245, 245, 245)); // cinza claro suave

        JButton btnAutor = criarBotao("Cadastrar Autor");
        JButton btnLivro = criarBotao("Cadastrar Livro");
        JButton btnUsuario = criarBotao("Cadastrar Usuário");
        JButton btnEmprestimo = criarBotao("Realizar Empréstimo");
        JButton btnDevolucao = criarBotao("Registrar Devolução");
        JButton btnMulta = criarBotao("Pagar Multas");

        painel.add(btnAutor);
        painel.add(btnLivro);
        painel.add(btnUsuario);
        painel.add(btnEmprestimo);
        painel.add(btnDevolucao);
        painel.add(btnMulta);

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // ====== AÇÕES ======
        btnAutor.addActionListener(e -> new TelaCadastrarAutor(service));
        btnLivro.addActionListener(e -> new TelaCadastrarLivro(service));
        btnUsuario.addActionListener(e -> new TelaCadastrarUsuario(service));
        btnEmprestimo.addActionListener(e -> new TelaEmprestimo(service));
        btnDevolucao.addActionListener(e -> new TelaDevolucao(service));
        btnMulta.addActionListener(e -> new TelaMulta(service));

        setVisible(true);
    }

    // Método para estilizar botões
    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(30, 144, 255));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(20, 110, 210)));
        return btn;
    }
}
