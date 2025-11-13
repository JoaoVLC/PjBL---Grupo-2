package main.model;

public class LivroFisico extends Livro {

    public LivroFisico(String titulo, Autor autor, String isbn) {
        super(titulo, autor, isbn);
    }

    @Override
    public boolean isDisponivel() {
        return disponivel;
    }
}
    