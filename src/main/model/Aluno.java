package main.model;

public class Aluno extends Usuario {

    private String matricula;

    @Override
    public int calcularPrazoDevolucao() {
        return 7; // 7 dias para alunos
    }
}
