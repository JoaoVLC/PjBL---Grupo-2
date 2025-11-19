package main.model;

public abstract class Livro {
    protected String titulo;
    protected Autor autor;
    protected String isbn;
    protected boolean disponivel;

    public abstract boolean isDisponivel();

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return titulo + " | ISBN: " + isbn + " | Autor: " + autor.getNomeCompleto();
    }
}
