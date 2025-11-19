package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitUserWithMulta {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        Autor autor = svc.cadastrarAutor("A", "B", "BR");
        Livro livro = svc.cadastrarLivro("T", autor.getId(), "ISBN2", "fisico");
        Usuario aluno = svc.cadastrarUsuario("N", "U2", "aluno");
        aluno.adicionarMulta(10.0);

        try {
            svc.realizarEmprestimo(aluno, livro);
            System.out.println("FAIL: empréstimo deve ter sido bloqueado por multa.");
            System.exit(2);
        } catch (BibliotecaException ex) {
            System.out.println("PASS: empréstimo bloqueado por multa: " + ex.getMessage());
            System.exit(0);
        }
    }
}
