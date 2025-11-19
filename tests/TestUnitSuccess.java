package main.ui.tests;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

public class TestUnitSuccess {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();
        // usar API do service para criar autor/livro/usuario
        Autor autor = svc.cadastrarAutor("A", "B", "BR");
        Livro livro = svc.cadastrarLivro("T", autor.getId(), "ISBN1", "fisico");
        Usuario aluno = svc.cadastrarUsuario("N", "U1", "aluno");

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
