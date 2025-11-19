package main.model;

import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String id;
    protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    protected ArrayList<Multa> multas = new ArrayList<>();

    // Construtor
    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
        // multas já inicializada na declaração
    }

    // Emprestimos
    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // Multa: agora modelada como objetos Multa
    public void pagarMulta() {
        // quita todas as multas pendentes
        for (Multa m : multas) {
            if (!m.isPaga()) m.quitarMulta();
        }
    }

    public double getMulta() {
        double soma = 0;
        for (Multa m : multas) {
            if (!m.isPaga()) soma += m.getValor();
        }
        return soma;
    }

    public void adicionarMulta(double valor) {
        Multa m = new Multa(valor);
        multas.add(m);
    }

    public java.util.List<Multa> getMultas() {
        return multas;
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
        return nome + " | ID: " + id + " | Multa: R$ " + getMulta();
    }
}
