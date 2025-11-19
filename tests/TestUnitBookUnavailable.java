package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitBookUnavailable {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        Autor autor = svc.cadastrarAutor("A", "B", "BR");
        Livro livro = svc.cadastrarLivro("T", autor.getId(), "ISBN3", "fisico");
        if (livro != null) livro.setDisponivel(false); // já emprestado
        Usuario aluno = svc.cadastrarUsuario("N", "U3", "aluno");

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
