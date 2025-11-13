package main.service;

import main.model.*;
import main.exception.BibliotecaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarUsuario(Usuario u) { usuarios.add(u); }
    public void cadastrarLivro(Livro l) { livros.add(l); }
    public void cadastrarAutor(Autor a) { /* salvar autor em lista ou arquivo */ }

    public void realizarEmprestimo(Usuario u, Livro l) throws BibliotecaException {
        if (u.getMulta() > 0) throw new BibliotecaException("Usuário possui multa pendente!");
        if (!l.isDisponivel()) throw new BibliotecaException("Livro indisponível!");

        LocalDate hoje = LocalDate.now();
        LocalDate devolucao = hoje.plusDays(u.calcularPrazoDevolucao());

        Emprestimo e = new Emprestimo(l, u, hoje, devolucao);
        emprestimos.add(e);
        u.adicionarEmprestimo(e);
        l.setDisponivel(false);
    }

    public void registrarDevolucao(Emprestimo e) {
        e.setDataDevolucaoReal(LocalDate.now());
        e.getLivro().setDisponivel(true);
    }

    public List<Livro> listarLivros() { return livros; }
    public List<Usuario> listarUsuarios() { return usuarios; }
}
