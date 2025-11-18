package main.model;

public class Professor extends Usuario {

    private String numeroRH;

    @Override
    public int calcularPrazoDevolucao() {
        return 14; // 14 dias para professores
    }
}

