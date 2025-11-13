package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String nome;
    protected String id;
    protected double multa;
    protected List<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.multa = 0.0;
    }

    public abstract int calcularPrazoDevolucao();

    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public void pagarMulta() {
        multa = 0.0;
    }

    public String getNome() { return nome; }
    public String getId() { return id; }
    public double getMulta() { return multa; }
}
