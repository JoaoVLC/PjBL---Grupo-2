package main.service;

import main.model.*;
import main.exception.BibliotecaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


public class BibliotecaService {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Autor> autores = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarAutor(Autor a) {
        autores.add(a);
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public void cadastrarLivro(Livro l) {
        livros.add(l);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void realizarEmprestimo(Usuario u, Livro l) throws BibliotecaException {
        // Verifica se usuário tem multa/pendência
        if (u.getMulta() > 0) {
            throw new BibliotecaException("Usuário possui multa pendente e não pode realizar empréstimo.");
        }

        // Verifica disponibilidade do livro
        if (!l.isDisponivel()) {
            throw new BibliotecaException("Livro não está disponível para empréstimo.");
        }

        // Registrar datas: data de retirada = hoje, data prevista = hoje + prazo do usuário
        Date hoje = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        cal.add(Calendar.DATE, u.calcularPrazoDevolucao());
        Date prevista = cal.getTime();

        // Criar empréstimo e registrar
        Emprestimo e = new Emprestimo(l, u, hoje, prevista);
        emprestimos.add(e);
        u.adicionarEmprestimo(e);

        // Atualizar status do livro para emprestado
        l.setDisponivel(false);
    }

    public void registrarDevolucao(Usuario u, Livro l) {
        // Procura empréstimo ativo para o usuário e livro
        Emprestimo encontrado = null;
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario() == u && e.getLivro() == l && e.getDataDevolucao() == null) {
                encontrado = e;
                break;
            }
        }

        if (encontrado == null) {
            // Não encontrou empréstimo ativo — nada a fazer
            return;
        }

        // Marca data de devolução como hoje
        Date hoje = new Date();
        encontrado.setDataDevolucao(hoje);

        // Calcula multa e acumula no usuário, se houver
        double multa = encontrado.calcularMulta();
        if (multa > 0) {
            u.adicionarMulta(multa);
        }

        // Libera o livro
        l.setDisponivel(true);
    }

    // Método auxiliar para testes: adiciona um empréstimo já criado (útil para simular datas)
    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
        e.getUsuario().adicionarEmprestimo(e);
        e.getLivro().setDisponivel(false);
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void listarPendencias() {
        // implementar depois
    }
}
