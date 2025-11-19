package main.model;

public class LivroDigital extends Livro {
    public LivroDigital() {
    }

    public LivroDigital(String titulo, Autor autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    @Override
    public boolean isDisponivel() {
        return true; // digitais sempre disponíveis
    }
}
