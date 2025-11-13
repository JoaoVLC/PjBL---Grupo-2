package main.model;

public class LivroDigital extends Livro {

    public LivroDigital(String titulo, Autor autor, String isbn) {
        super(titulo, autor, isbn);
    }

    @Override
    public boolean isDisponivel() {
        return true; // livros digitais sempre disponíveis
    }
}
