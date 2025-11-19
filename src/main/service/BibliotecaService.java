package main.service;

import main.model.*;
import main.exception.BibliotecaException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BibliotecaService {

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

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

    public void cadastrarLivro(Livro l) {
        livros.add(l);
    }

    public Livro cadastrarLivro(String titulo, int idAutor, String isbn, String tipo) {
        Autor a = buscarAutorPorId(idAutor);
        if (a == null) {
            System.out.println("Autor não encontrado com id: " + idAutor);
            return null;
        }
        Livro l;
        if (tipo.equalsIgnoreCase("fisico") || tipo.equalsIgnoreCase("f")) {
            l = new LivroFisico(titulo, a, isbn);
        } else {
            l = new LivroDigital(titulo, a, isbn);
        }
        livros.add(l);
        return l;
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
            throw new BibliotecaException("Usuário possui multa pendente e não pode realizar empréstimo.");
        }

        if (!l.isDisponivel()) {
            throw new BibliotecaException("Livro não está disponível para empréstimo.");
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
        if (multa > 0) {
            // criar registro de Multa e associar ao usuário
            u.adicionarMulta(multa);
        }

        // tornar o livro disponível novamente
        l.setDisponivel(true);
    }

    /**
     * Quita todas as multas pendentes do usuário e retorna o total quitado.
     */
    public double quitarMultasUsuario(Usuario u) {
        double total = 0;
        for (Multa m : u.getMultas()) {
            if (!m.isPaga()) {
                total += m.getValor();
                m.quitarMulta();
            }
        }
        return total;
    }

    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
        e.getUsuario().adicionarEmprestimo(e);
        e.getLivro().setDisponivel(false);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // =================== PENDÊNCIAS ===================
    public void listarPendencias() {
        // Implementar lógica depois, se necessário
    }
}
