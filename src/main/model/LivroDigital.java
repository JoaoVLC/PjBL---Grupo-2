package main.model;

public class LivroDigital extends Livro {

    @Override
    public boolean isDisponivel() {
        return true; // digitais sempre disponíveis
    }
}
