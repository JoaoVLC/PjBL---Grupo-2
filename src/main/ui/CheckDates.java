package main.ui;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

import java.text.SimpleDateFormat;

public class CheckDates {
    public static void main(String[] args) {
        BibliotecaService svc = new BibliotecaService();

        Autor autor = svc.cadastrarAutor("Teste", "Autor", "BR");
        Livro livro = svc.cadastrarLivro("DataBook", autor.getId(), "ISBN-DATE", "fisico");
        Usuario u = svc.cadastrarUsuario("Ana", "U10", "aluno");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        try {
            svc.realizarEmprestimo(u, livro);
            // pegar último empréstimo do usuário
            Emprestimo e = u.getEmprestimos().get(u.getEmprestimos().size() - 1);
            System.out.println("DataEmprestimo: " + fmt.format(e.getDataEmprestimo()));
            System.out.println("DataPrevista: " + fmt.format(e.getDataPrevista()));
        } catch (BibliotecaException ex) {
            System.out.println("Erro inesperado: " + ex.getMessage());
        }

        // Teste: usuário com multa deve ser bloqueado
        Usuario u2 = svc.cadastrarUsuario("Bob", "U11", "aluno");
        u2.adicionarMulta(5.0);
        Livro livro2 = svc.cadastrarLivro("BlockedBook", autor.getId(), "ISBN-BLOCK", "fisico");
        try {
            svc.realizarEmprestimo(u2, livro2);
            System.out.println("ERRO: empréstimo deveria ter sido bloqueado por multa.");
        } catch (BibliotecaException ex) {
            System.out.println("Bloqueado por multa (ok): " + ex.getMessage());
        }

        // Teste: livro indisponível
        Livro livro3 = svc.cadastrarLivro("UnavailableBook", autor.getId(), "ISBN-NO", "fisico");
        livro3.setDisponivel(false);
        Usuario u3 = svc.cadastrarUsuario("Carol", "U12", "aluno");
        try {
            svc.realizarEmprestimo(u3, livro3);
            System.out.println("ERRO: empréstimo deveria ter sido bloqueado por indisponibilidade.");
        } catch (BibliotecaException ex) {
            System.out.println("Bloqueado por indisponibilidade (ok): " + ex.getMessage());
        }
    }
}
