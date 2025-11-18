package main.service;

import main.model.*;
import main.exception.BibliotecaException;

import java.util.ArrayList;


public class BibliotecaService {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarAutor(Autor a) {
        // implementa depois
    }

    public void cadastrarLivro(Livro l) {
        livros.add(l);
    }

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void realizarEmprestimo(Usuario u, Livro l) throws BibliotecaException {
        // implementar depois
    }

    public void registrarDevolucao(Usuario u, Livro l) {
        // implementar depois
    }

    public void listarPendencias() {
        // implementar depois
    }
}
