package main.model;

public class LivroFisico extends Livro {
    // construtor padrão - [mantido para possíveis frameworks; não usado frequentemente]
    public LivroFisico() {
        // construtor padrão
    }

    // construtor principal - [define título, autor, isbn e disponibilidade inicial]
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

    