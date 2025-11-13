package main.model;

public abstract class Livro {
    protected String titulo;
    protected Autor autor;
    protected String isbn;
    protected boolean disponivel;

    public Livro(String titulo, Autor autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public abstract boolean isDisponivel();

    public String getTitulo() { return titulo; }
    public Autor getAutor() { return autor; }
    public String getIsbn() { return isbn; }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
