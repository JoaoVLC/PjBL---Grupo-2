package main.model;

import java.util.ArrayList;

public abstract class Usuario {
    protected String nome;
    protected String id;
    protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    protected ArrayList<Multa> multas = new ArrayList<>();

    // construtor - [inicializa nome e id]
    public Usuario(String nome, String id) {
        this.nome = nome;
        this.id = id;
        // multas já inicializada na declaração
    }

    // metodo - [adiciona um empréstimo ao histórico do usuário]
    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    // metodo - [retorna lista de empréstimos]
    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    // metodo - [marca todas as multas como quitadas]
    public void pagarMulta() {
        // quita todas as multas pendentes
        for (Multa m : multas) {
            if (!m.isPaga()) m.quitarMulta();
        }
    }

    // metodo - [retorna soma das multas não pagas]
    public double getMulta() {
        double soma = 0;
        for (Multa m : multas) {
            if (!m.isPaga()) soma += m.getValor();
        }
        return soma;
    }

    // metodo - [cria e adiciona multa para o usuário]
    public void adicionarMulta(double valor) {
        Multa m = new Multa(valor);
        multas.add(m);
    }

    // metodo - [retorna lista de objetos Multa]
    public java.util.List<Multa> getMultas() {
        return multas;
    }

    // getter - [retorna o nome do usuário]
    public String getNome() {
        return nome;
    }

    // getter - [retorna o id (matrícula ou RH)]
    public String getId() {
        return id;
    }

    // metodo abstrato - [cada subclasse define o prazo de devolução]
    public abstract int calcularPrazoDevolucao();

    @Override
    public String toString() {
        return nome + " | ID: " + id + " | Multa: R$ " + getMulta();
    }
}
