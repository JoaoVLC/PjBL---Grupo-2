package main.model;

public class Aluno extends Usuario {

    private String matricula;
    @Override
    public int calcularPrazoDevolucao() {
        return 7; // 7 dias para alunos
    }

    public Aluno() {
        // construtor padrão
    }

    public Aluno(String nome, String id, String matricula) {
        this.nome = nome;
        this.id = id;
        this.matricula = matricula;
        this.multa = 0;
    }
}
