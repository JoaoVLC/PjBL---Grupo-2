package main.service;

import main.model.*;
import main.exception.BibliotecaException;
import java.util.*;

public class BibliotecaService {

    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;

    public BibliotecaService() {
        autores = new ArrayList<>();
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    // =================== AUTORES ===================
    public Autor cadastrarAutor(String nome, String sobrenome, String nacionalidade) {

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

    // =================== USUÁRIOS ===================
    public Usuario cadastrarUsuario(String nome, String id, String tipo) {

        if (buscarUsuarioPorId(id) != null) {
            System.out.println("Já existe um usuário com esse ID!");
            return null;
        }

        Usuario novo;

        if (tipo.equalsIgnoreCase("aluno")) {
            novo = new Aluno(nome, id);
        } else if (tipo.equalsIgnoreCase("professor")) {
            novo = new Professor(nome, id);
        } else {
            System.out.println("Tipo inválido! Use 'aluno' ou 'professor'.");
            return null;
        }

        usuarios.add(novo);
        return novo;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // =================== LIVROS ===================
    public Livro cadastrarLivro(String titulo, int idAutor, String isbn, String tipo) {

        Autor autor = buscarAutorPorId(idAutor);
        if (autor == null) {
            System.out.println("Autor não encontrado!");
            return null;
        }

        if (buscarLivroPorISBN(isbn) != null) {
            System.out.println("Já existe um livro com esse ISBN!");
            return null;
        }

        Livro novo;

        if (tipo.equalsIgnoreCase("fisico")) {
            novo = new LivroFisico(titulo, autor, isbn);
        }
        else if (tipo.equalsIgnoreCase("digital")) {
            novo = new LivroDigital(titulo, autor, isbn);
        }
        else {
            System.out.println("Tipo inválido!");
            return null;
        }

        livros.add(novo);
        return novo;
    }

    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro l : livros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) return l;
        }
        return null;
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    // =================== EMPRÉSTIMOS ===================

    public void realizarEmprestimo(Usuario u, Livro l) throws BibliotecaException {

        if (u.getMulta() > 0) {
            throw new BibliotecaException("Usuário possui multa pendente.");
        }

        if (!l.isDisponivel()) {
            throw new BibliotecaException("Livro indisponível.");
        }

        Date hoje = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        cal.add(Calendar.DATE, u.calcularPrazoDevolucao());
        Date prevista = cal.getTime();

        Emprestimo e = new Emprestimo(l, u, hoje, prevista);
        emprestimos.add(e);

        u.adicionarEmprestimo(e);
        l.setDisponivel(false);
    }

    public void registrarDevolucao(Usuario u, Livro l) {

        Emprestimo encontrado = null;

        for (Emprestimo e : emprestimos) {
            if (e.getUsuario() == u && e.getLivro() == l && e.getDataDevolucao() == null) {
                encontrado = e;
                break;
            }
        }

        if (encontrado == null) return;

        Date hoje = new Date();
        encontrado.setDataDevolucao(hoje);

        double multa = encontrado.calcularMulta();
        if (multa > 0) u.adicionarMulta(multa);

        l.setDisponivel(true);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public double quitarMultasUsuario(Usuario u) {
        double total = u.getMulta();
        u.pagarMulta();
        return total;
    }

    public void listarPendencias() {
        // opcional
    }
}
