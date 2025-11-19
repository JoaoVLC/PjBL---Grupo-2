package main.model;

public abstract class Livro {
    protected String titulo;
    protected Autor autor;
    protected String isbn;
    protected boolean disponivel;

    public abstract boolean isDisponivel();

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }
}

