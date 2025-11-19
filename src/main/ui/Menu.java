package main.ui;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

import java.util.Scanner;
import java.util.List;

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

        Autor autor = service.cadastrarAutor(nome, sobrenome, nac);
        if (autor != null) System.out.println("Autor cadastrado: " + autor);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        List<Autor> autores = service.listarAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado. Cadastre um autor antes.");
            return;
        }

        System.out.println("Autores disponíveis:");
        for (Autor a : autores) {
            System.out.println(a.getId() + " - " + a.getNomeCompleto());
        }

        System.out.print("Digite o ID do autor: ");
        int idAutor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tipo (fisico/digital): ");
        String tipo = scanner.nextLine();

        Livro livro = service.cadastrarLivro(titulo, idAutor, isbn, tipo);
        if (livro != null) System.out.println("Livro cadastrado: " + livro);
    }

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
        if (usuario != null) System.out.println("Usuário cadastrado: " + usuario);
    }

    private void realizarEmprestimo() {
        System.out.print("Digite o ID do usuário: ");
        String idUser = scanner.nextLine();
        Usuario usuario = service.buscarUsuarioPorId(idUser);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("Livros disponíveis:");
        for (Livro l : service.listarLivros()) {
            if (l.isDisponivel()) System.out.println(l.getIsbn() + " - " + l.getTitulo());
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

    private void registrarDevolucao() {
        System.out.print("Digite o ID do usuário: ");
        String idUser = scanner.nextLine();
        Usuario usuario = service.buscarUsuarioPorId(idUser);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        Livro livro = service.buscarLivroPorISBN(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        service.registrarDevolucao(usuario, livro);
        System.out.println("Devolução registrada. Multa atual do usuário: " + usuario.getMulta());
    }
}
