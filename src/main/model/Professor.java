package main.model;

public class Professor extends Usuario {

    private String numeroRH;

    @Override
    public int calcularPrazoDevolucao() {
        return 14; // 14 dias para professores
    }

    public Professor(String nome, String id) {
        super(nome, id);
        this.numeroRH = "";
    }

    public Professor(String nome, String id, String numeroRH) {
        super(nome, id);
        this.numeroRH = numeroRH;
    }
}

