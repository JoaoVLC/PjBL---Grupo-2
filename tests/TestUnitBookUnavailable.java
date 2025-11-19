package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitBookUnavailable {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        Autor autor = new Autor("A","B","BR");
        svc.cadastrarAutor(autor);
    LivroFisico livro = new LivroFisico("T", autor, "ISBN3");
        livro.setDisponivel(false); // já emprestado
        svc.cadastrarLivro(livro);
        Aluno aluno = new Aluno("N","U3","M3");
        svc.cadastrarUsuario(aluno);

        try {
            svc.realizarEmprestimo(aluno, livro);
            System.out.println("FAIL: empréstimo deve ter sido bloqueado por indisponibilidade do livro.");
            System.exit(2);
        } catch (BibliotecaException ex) {
            System.out.println("PASS: empréstimo bloqueado por indisponibilidade: " + ex.getMessage());
            System.exit(0);
        }
    }
}
