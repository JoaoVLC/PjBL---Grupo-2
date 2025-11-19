package main.model;

import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String id;
    protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    protected double multa;

    // Construtor
    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.multa = 0;
    }

    // Emprestimos
    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // Multa
    public void pagarMulta() {
        this.multa = 0;
    }

    public double getMulta() {
        return multa;
    }

    public void adicionarMulta(double valor) {
        this.multa += valor;
    }

    // Getters básicos
    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    // Método abstrato
    public abstract int calcularPrazoDevolucao();

    @Override
    public String toString() {
        return nome + " | ID: " + id + " | Multa: R$ " + multa;
    }
}
