package main.model;

public abstract class Livro {
    protected String titulo;
    protected Autor autor;
    protected String isbn;
    protected boolean disponivel;

    // metodo abstrato - [define disponibilidade de forma polimórfica]
    public abstract boolean isDisponivel();

    // getters e setter - [acesso/controle dos atributos]
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

    // metodo - [permite alterar disponibilidade (usado em empréstimo/devolução)]
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return titulo + " | ISBN: " + isbn + " | Autor: " + autor.getNomeCompleto();
    }
}
