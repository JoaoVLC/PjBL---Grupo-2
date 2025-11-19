package main.ui;

import main.model.*;
import main.service.BibliotecaService;

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
            System.out.println("\n==== SISTEMA DE BIBLIOTECA ====");
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
                case 1 -> cadastrarAutor();
                case 2 -> cadastrarLivro();
                case 3 -> cadastrarUsuario();
                case 4 -> realizarEmprestimo();
                case 5 -> registrarDevolucao();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarAutor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nac = scanner.nextLine();

        Autor autor = new Autor(nome, sobrenome, nac);
        service.cadastrarAutor(autor);

        System.out.println("Autor cadastrado com sucesso!");
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        // Listar autores para selecionar
        System.out.println("Escolha o autor (digite o índice):");
        var autores = service.getAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado. Cadastre um autor antes de cadastrar um livro.");
            return;
        }
        for (int i = 0; i < autores.size(); i++) {
            System.out.println(i + " - " + autores.get(i).getNome());
        }

        int indexAutor = scanner.nextInt();
        scanner.nextLine();

        if (indexAutor < 0 || indexAutor >= autores.size()) {
            System.out.println("Índice de autor inválido.");
            return;
        }

        Autor autorEscolhido = autores.get(indexAutor);

        System.out.print("Tipo do livro (1-Físico, 2-Digital): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Livro l = (tipo == 1)
                ? new LivroFisico(titulo, autorEscolhido, isbn)
                : new LivroDigital(titulo, autorEscolhido, isbn);

        service.cadastrarLivro(l);

        System.out.println("Livro cadastrado com sucesso!");
    }

    private void cadastrarUsuario() {
        System.out.print("Tipo (1-Aluno / 2-Professor): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("ID do usuário: ");
        String id = scanner.nextLine();

        Usuario usuario;

        if (tipo == 1) {
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
            usuario = new Aluno(nome, id, matricula);
        } else {
            System.out.print("Número RH: ");
            String rh = scanner.nextLine();
            usuario = new Professor(nome, id, rh);
        }

        service.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado!");
    }

    private void realizarEmprestimo() {
        var usuarios = service.getUsuarios();
        var livros = service.getLivros();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("Escolha o usuário (índice):");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + " - " + usuarios.get(i).getNome());
        }
        int iu = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escolha o livro (índice):");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(i + " - " + livros.get(i).getTitulo() + " (disponível: " + livros.get(i).isDisponivel() + ")");
        }
        int il = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarios.get(iu);
        Livro livro = livros.get(il);

        try {
            service.realizarEmprestimo(usuario, livro);
            System.out.println("Empréstimo realizado com sucesso. Data de retirada: hoje. Data prevista calculada pelo usuário.");
        } catch (Exception ex) {
            System.out.println("Falha ao realizar empréstimo: " + ex.getMessage());
        }
    }

    private void registrarDevolucao() {
        var usuarios = service.getUsuarios();
        var livros = service.getLivros();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("Escolha o usuário (índice):");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + " - " + usuarios.get(i).getNome());
        }
        int iu = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escolha o livro (índice):");
        for (int i = 0; i < livros.size(); i++) {
            System.out.println(i + " - " + livros.get(i).getTitulo());
        }
        int il = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarios.get(iu);
        Livro livro = livros.get(il);

        service.registrarDevolucao(usuario, livro);
        System.out.println("Devolução registrada (se havia empréstimo ativo). Multa atual do usuário: " + usuario.getMulta());
    }
}

