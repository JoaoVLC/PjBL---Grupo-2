package main.model;

public class Autor {
    private int idAutor;
    private String nome;
    private String sobrenome;
    private String nacionalidade;
    private static int contadorIdAutor = 1;


    public Autor(String nome, String sobrenome, String nacionalidade) {
        this.idAutor = contadorIdAutor++;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
    }

    public int getId() {
        return idAutor;
    }

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    @Override
    public String toString() {
        return "#" + idAutor + " - " + nome + " " + sobrenome + " (" + nacionalidade + ")";
    }
}
