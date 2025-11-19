package main.service;

import main.model.*;
import main.exception.BibliotecaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // CRUD - AUTORES

    public Autor cadastrarAutor(String nome, String sobrenome, String nacionalidade) {

        // Evita duplicar autor pelo nome completo
        for (Autor a : autores) {
            if (a.getNomeCompleto().equalsIgnoreCase(nome + " " + sobrenome)) {
                System.out.println("Autor já existe!");
                return null;
            }
        }

        Autor novo = new Autor(nome, sobrenome, nacionalidade);
        autores.add(novo);

        return novo;
    }

    public List<Autor> listarAutores() {
        return autores;
    }

    public Autor buscarAutorPorId(int idAutor) {
        for (Autor a : autores) {
            if (a.getId() == idAutor) return a;
        }
        return null;
    }

    // CRUD - USUÁRIOS

    public Usuario cadastrarUsuario(String nome, String id, String tipo) {

        // Verifica duplicidade
        if (buscarUsuarioPorId(id) != null) {
            System.out.println("Já existe um usuário com esse ID!");
            return null;
        }

        Usuario novo;

        if (tipo.equalsIgnoreCase("aluno")) {
            novo = new Aluno(nome, id);
        }
        else if (tipo.equalsIgnoreCase("professor")) {
            novo = new Professor(nome, id);
        }
        else {
            System.out.println("Tipo inválido! Use 'aluno' ou 'professor'.");
            return null;
        }

        usuarios.add(novo);
        return novo;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // CRUD - LIVROS



    // EMPRÉSTIMO

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
}