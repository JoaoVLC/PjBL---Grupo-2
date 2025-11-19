package main.model;

public class Autor {
    private int idAutor;
    private String nome;
    private String sobrenome;
    private String nacionalidade;
    private static int contadorIdAutor = 1;

    // construtor - [cria autor e atribui id automático]
    public Autor(String nome, String sobrenome, String nacionalidade) {
        this.idAutor = contadorIdAutor++;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
    }

    // getter - [retorna id do autor]
    public int getId() {
        return idAutor;
    }

    // metodo - [retorna nome completo concatenado]
    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }

    // getter - [retorna nacionalidade]
    public String getNacionalidade() {
        return nacionalidade;
    }

    @Override
    public String toString() {
        return "#" + idAutor + " - " + nome + " " + sobrenome + " (" + nacionalidade + ")";
    }
}
