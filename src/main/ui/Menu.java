package main.ui;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

import java.util.Scanner;

public class Menu {
    private BibliotecaService service;
    private Scanner scanner = new Scanner(System.in);

    public Menu(BibliotecaService service) {
        this.service = service;
    }

    public void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n==== SISTEMA BIBLIOTECA ====");
            System.out.println("1. Cadastrar Autor");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Cadastrar Usuário");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Registrar Devolução");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAutor();
                    break;
                case 2:
                    cadastrarLivro();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    realizarEmprestimo();
                    break;
                case 5:
                    registrarDevolucao();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (opcao != 0);
    }

    // CADASTRAR AUTOR

    private void cadastrarAutor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Nacionalidade: ");
        String nac = scanner.nextLine();

        Autor autor = service.cadastrarAutor(nome, sobrenome, nac);

        if (autor != null)
            System.out.println("Autor cadastrado: " + autor);
    }


    // CADASTRAR LIVRO

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        // Mostrar autores disponíveis
        System.out.println("\nAutores cadastrados:");
        for (Autor a : service.listarAutores()) {
            System.out.println(a);
        }

        System.out.print("Digite o ID do autor: ");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tipo (fisico/digital): ");
        String tipo = scanner.nextLine();

        Livro livro = service.cadastrarLivro(titulo, idAutor, isbn, tipo);

        if (livro != null)
            System.out.println("Livro cadastrado: " + livro);
    }

    // CADASTRAR USUÁRIO

    private void cadastrarUsuario() {
        System.out.print("Tipo (1-Aluno / 2-Professor): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Identificador (Matrícula ou RH): ");
        String id = scanner.nextLine();

        String tipoUser = (tipo == 1) ? "aluno" : "professor";

        Usuario usuario = service.cadastrarUsuario(nome, id, tipoUser);

        if (usuario != null)
            System.out.println("Usuário cadastrado: " + usuario);
    }

    // REALIZAR EMPRÉSTIMO

    private void realizarEmprestimo() {
        System.out.println("\nUsuários:");
        for (Usuario u : service.listarUsuarios()) {
            System.out.println(u);
        }

        System.out.print("Digite o ID do usuário: ");
        String id = scanner.nextLine();

        Usuario usuario = service.buscarUsuarioPorId(id);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("\nLivros disponíveis:");
        for (Livro l : service.listarLivros()) {
            if (l.isDisponivel())
                System.out.println(l.getIsbn() + " - " + l.getTitulo());
        }

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        Livro livro = service.buscarLivroPorISBN(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        try {
            service.realizarEmprestimo(usuario, livro);
            System.out.println("Empréstimo realizado com sucesso!");
        } catch (BibliotecaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // DEVOLUÇÃO (simples)
    private void registrarDevolucao() {
        System.out.println("Função será implementada em breve...");
    }
}
