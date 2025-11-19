package main.model;

public class Aluno extends Usuario {

    private String matricula;
    @Override
    public int calcularPrazoDevolucao() {
        return 7; // 7 dias para alunos
    }

    public Aluno(String nome, String id) {
        super(nome, id);
        this.matricula = "";
    }

    public Aluno(String nome, String id, String matricula) {
        super(nome, id);
        this.matricula = matricula;
    }
}
