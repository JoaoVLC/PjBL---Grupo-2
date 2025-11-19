package main.model;

public class LivroFisico extends Livro {
    public LivroFisico() {
        // construtor padrão
    }

    public LivroFisico(String titulo, Autor autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    @Override
    public boolean isDisponivel() {
        return disponivel;
    }
}

    