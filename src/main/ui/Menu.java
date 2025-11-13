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
                    System.out.println("Em breve: devolução...");
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

    private void cadastrarAutor() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nac = scanner.nextLine();
        Autor autor = new Autor(nome, sobrenome, nac);
        service.cadastrarAutor(autor);
        System.out.println("Autor cadastrado!");
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        Livro livro = new LivroFisico(titulo, null, isbn); // autor nulo por enquanto
        service.cadastrarLivro(livro);
        System.out.println("Livro cadastrado!");
    }

    private void cadastrarUsuario() {
        System.out.print("Tipo (1-Aluno / 2-Professor): ");
        int tipo = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Identificador (Matrícula ou RH): ");
        String id = scanner.nextLine();
        Usuario usuario = (tipo == 1) ? new Aluno(nome, id) : new Professor(nome, id);
        service.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado!");
    }

    private void realizarEmprestimo() {
        try {
            System.out.println("Empréstimo realizado com sucesso (lógica simplificada)!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
