package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitSuccess {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        Autor autor = new Autor("A","B","BR");
        svc.cadastrarAutor(autor);
    LivroFisico livro = new LivroFisico("T", autor, "ISBN1");
        svc.cadastrarLivro(livro);
        Aluno aluno = new Aluno("N","U1","M1");
        svc.cadastrarUsuario(aluno);

        try {
            svc.realizarEmprestimo(aluno, livro);
            if (!livro.isDisponivel() && aluno.getEmprestimos().size() == 1) {
                System.out.println("PASS: Empréstimo realizado com sucesso.");
                System.exit(0);
            } else {
                System.out.println("FAIL: estado inconsistente após empréstimo.");
                System.exit(2);
            }
        } catch (BibliotecaException ex) {
            System.out.println("FAIL: exceção inesperada: " + ex.getMessage());
            System.exit(3);
        }
    }
}
