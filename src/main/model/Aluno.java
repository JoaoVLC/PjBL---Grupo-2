package main.model;

public class Aluno extends Usuario {

    private String matricula;

    // metodo sobrescrito - [define prazo de devolução para aluno]
    @Override
    public int calcularPrazoDevolucao() {
        return 7; // 7 dias para alunos
    }

    // construtor - [cria Aluno com matrícula vazia]
    public Aluno(String nome, String id) {
        super(nome, id);
        this.matricula = "";
    }

    // construtor - [cria Aluno definindo matrícula]
    public Aluno(String nome, String id, String matricula) {
        super(nome, id);
        this.matricula = matricula;
    }
}
