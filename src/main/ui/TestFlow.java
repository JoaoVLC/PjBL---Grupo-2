package main.ui;

import main.model.*;
import main.service.BibliotecaService;
import main.exception.BibliotecaException;

import java.util.Calendar;
import java.util.Date;

public class TestFlow {
    public static void main(String[] args) throws Exception {
        BibliotecaService svc = new BibliotecaService();

    // criar dados iniciais via service
    Autor autor = svc.cadastrarAutor("Joao", "Silva", "BR");
    Livro livro = svc.cadastrarLivro("Java 101", autor.getId(), "ISBN001", "fisico");
    Usuario aluno = svc.cadastrarUsuario("Maria", "U1", "aluno");

        System.out.println("Disponibilidade antes: " + livro.isDisponivel());

        // Realizar empréstimo normal
        svc.realizarEmprestimo(aluno, livro);
        System.out.println("Disponibilidade após empréstimo: " + livro.isDisponivel());

        // Registrar devolução (sem multa)
        svc.registrarDevolucao(aluno, livro);
        System.out.println("Disponibilidade após devolução: " + livro.isDisponivel());
        System.out.println("Multa do usuário (depois devolução imediata): " + aluno.getMulta());

        // Simular empréstimo atrasado criando um empréstimo com data prevista no passado
    Livro livro2 = svc.cadastrarLivro("Java Avançado", autor.getId(), "ISBN002", "fisico");
    Usuario aluno2 = svc.cadastrarUsuario("Carlos", "U2", "aluno");

        // dataEmprestimo = 15 dias atrás
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -15);
        Date retirada = cal.getTime();

        // dataPrevista = retirada + 7 dias (ou seja, 8 dias atrás)
        Calendar c2 = Calendar.getInstance();
        c2.setTime(retirada);
        c2.add(Calendar.DATE, 7);
        Date prevista = c2.getTime();

        // cria empréstimo manual (simula empréstimo antigo)
        Emprestimo e = new Emprestimo(livro2, aluno2, retirada, prevista);
        svc.adicionarEmprestimo(e);

        System.out.println("Disponibilidade livro2 após empréstimo manual: " + livro2.isDisponivel());

        // Registrar devolução (hoje) — deve gerar multa
        svc.registrarDevolucao(aluno2, livro2);
        System.out.println("Disponibilidade livro2 após devolução: " + livro2.isDisponivel());
        System.out.println("Multa do usuário2 (depois de devolução atrasada): " + aluno2.getMulta());
    }
}
