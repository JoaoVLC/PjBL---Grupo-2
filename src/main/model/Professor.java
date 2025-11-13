package main.model;

public class Professor extends Usuario {

    public Professor(String nome, String numeroRH) {
        super(nome, numeroRH);
    }

    @Override
    public int calcularPrazoDevolucao() {
        return 14; // 14 dias para professor
    }
}
