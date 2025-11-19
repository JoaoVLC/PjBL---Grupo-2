package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitUserWithMulta {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        Autor autor = new Autor("A","B","BR");
        svc.cadastrarAutor(autor);
    LivroFisico livro = new LivroFisico("T", autor, "ISBN2");
        svc.cadastrarLivro(livro);
        Aluno aluno = new Aluno("N","U2","M2");
        aluno.adicionarMulta(10.0);
        svc.cadastrarUsuario(aluno);

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
