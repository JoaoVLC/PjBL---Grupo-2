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
        for (int i = 0; i < autores.size(); i++) {
            System.out.println(i + " - " + autores.get(i).getNome());
        }

        int indexAutor = scanner.nextInt();
        scanner.nextLine();

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
        // implementaremos depois
        System.out.println("Função ainda não implementada.");
    }

    private void registrarDevolucao() {
        // implementaremos depois
        System.out.println("Função ainda não implementada.");
    }
}

