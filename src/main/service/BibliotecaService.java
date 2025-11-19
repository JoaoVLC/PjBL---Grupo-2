package main.service;

import main.model.*;
import main.exception.BibliotecaException;
import java.util.*;

/*
 * BibliotecaService
 * - Camada de serviço que contém a lógica do sistema (regras de negócio).
 * - Mantém coleções em memória (listas de usuários, autores, livros, empréstimos).
 * - Fornece métodos CRUD e ações (emprestar, devolver, quitar multas).
 */

public class BibliotecaService {

    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;

    // constantes de caminho de arquivos (opcionais; se não usar, podem ser removidas)
    private static final String ARQ_AUTORES = "src/main/resources/autores.txt";
    private static final String ARQ_USUARIOS = "src/main/resources/usuarios.txt";
    private static final String ARQ_LIVROS = "src/main/resources/livros.txt";

    // construtor - [inicializa listas e tenta carregar arquivos via ArquivoTxtService]
    public BibliotecaService() {
        autores = new ArrayList<>();
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();

        try {
            ArquivoTxtService.carregarAutores(ARQ_AUTORES, this);
            ArquivoTxtService.carregarUsuarios(ARQ_USUARIOS, this);
            ArquivoTxtService.carregarLivros(ARQ_LIVROS, this);
        } catch (Exception e) {
            System.out.println("Nenhum arquivo encontrado. Iniciando vazio.");
        }

    }

    // =================== AUTORES ===================

    public Autor cadastrarAutor(String nome, String sobrenome, String nacionalidade) {

        // valida duplicidade por nome completo (case-insensitive)
        for (Autor a : autores) {
            if (a.getNomeCompleto().equalsIgnoreCase(nome + " " + sobrenome)) {
                System.out.println("Autor já existe!");
                return null;
            }
        }

        // criar novo autor e adicionar à lista
        Autor novo = new Autor(nome, sobrenome, nacionalidade);
        autores.add(novo);

        // opcional: salvar em arquivo (captura exceção para não interromper execução)
        try {
            ArquivoTxtService.salvarAutores(ARQ_AUTORES, autores);
        } catch (Exception e) {
            System.out.println("ERRO ao salvar autores no arquivo!");
        }

        return novo;
    }

    // listarAutores - [retorna lista de autores em memória]
    public List<Autor> listarAutores() {
        return autores;
    }

    // buscarAutorPorId - [procura autor pelo idAutor]
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

        // opcional: salvar em arquivo
        try {
            ArquivoTxtService.salvarUsuarios(ARQ_USUARIOS, usuarios);
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuários");
        }

        return novo;
    }

    // buscarUsuarioPorId - [procura usuário pelo id (case-insensitive)]
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    // listarUsuarios - [retorna lista de usuários em memória]
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

        // opcional: salvar em arquivo
        try {
            ArquivoTxtService.salvarLivros(ARQ_LIVROS, livros);
        } catch (Exception e) {
            System.out.println("Erro ao salvar livros");
        }

        return novo;
    }

    // buscarLivroPorISBN - [procura livro na lista por ISBN (case-insensitive)]
    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro l : livros) {
            if (l.getIsbn().equalsIgnoreCase(isbn)) return l;
        }
        return null;
    }

    // listarLivros - [retorna lista de livros em memória]
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

        // demonstração explícita de polimorfismo
        int dias = u.calcularPrazoDevolucao();
        System.out.println("Prazo de devolução para " + u.getNome() + ": " + dias + " dias.");

        // calcula data prevista de devolução
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

    // getEmprestimos - [retorna lista de empréstimos em memória]
    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo e) {

        // adiciona na lista normal
        emprestimos.add(e);

        // relaciona emprestimo ao usuário
        e.getUsuario().adicionarEmprestimo(e);

        // marca livro como indisponível
        e.getLivro().setDisponivel(false);
    }

    // quitarMultasUsuario - [retorna total antes de quitar e marca multas quitadas]
    public double quitarMultasUsuario(Usuario u) {
        double total = u.getMulta();
        u.pagarMulta();
        return total;
    }

    // listarPendencias - [espaço para implementar futura listagem de pendentes]
    public void listarPendencias() {
        // opcional: implementar listagem de usuários com multas ou empréstimos atrasados
    }
}
