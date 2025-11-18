package main.model;

import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String id;
    protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    protected double multa;

    public abstract int calcularPrazoDevolucao();

    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public void pagarMulta() {
        this.multa = 0;
    }

    // getters e setters
}

