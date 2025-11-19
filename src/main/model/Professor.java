package main.model;

public class Professor extends Usuario {

    private String numeroRH;

    // metodo sobrescrito - [define prazo de devolução para professor]
    @Override
    public int calcularPrazoDevolucao() {
        return 14; // 14 dias para professores
    }

    // construtor - [cria Professor com numeroRH vazio]
    public Professor(String nome, String id) {
        super(nome, id);
        this.numeroRH = "";
    }

    // construtor - [cria Professor com numeroRH informado]
    public Professor(String nome, String id, String numeroRH) {
        super(nome, id);
        this.numeroRH = numeroRH;
    }
}

