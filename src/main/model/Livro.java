package main.model;

public abstract class Livro {
    protected String titulo;
    protected Autor autor;
    protected String isbn;
    protected boolean disponivel;

    public abstract boolean isDisponivel();
}

