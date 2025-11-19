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

    public double getMulta() {
        return multa;
    }

    public void adicionarMulta(double valor) {
        this.multa += valor;
    }

    public String getNome() {
        return nome;
    }

    public java.util.ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // getters e setters
}

