package main.exception;

/*
 * BibliotecaException
 * - Classe de exceção customizada para regras do sistema (ex: usuário com multa, livro indisponível).
 * - Extende Exception para ser lançada e tratada onde necessário.
 */

public class BibliotecaException extends Exception {
    // construtor - [recebe a mensagem que descreve o erro e repassa para a superclasse Exception]
    public BibliotecaException(String mensagem) {
        super(mensagem);
    }
}
