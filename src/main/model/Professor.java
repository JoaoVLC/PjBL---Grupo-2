package main.model;

public class Professor extends Usuario {

    private String numeroRH;

    @Override
    public int calcularPrazoDevolucao() {
        return 14; // 14 dias para professores
    }

    public Professor() {
        // construtor padrão
    }

    public Professor(String nome, String id, String numeroRH) {
        this.nome = nome;
        this.id = id;
        this.numeroRH = numeroRH;
        this.multa = 0;
    }
}

