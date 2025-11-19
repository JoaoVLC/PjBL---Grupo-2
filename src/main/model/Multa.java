package main.model;

public class Multa {
    private double valor;
    private boolean paga;

    // construtor - [define valor da multa; por padrão, não paga]
    public Multa(double valor) {
        this.valor = valor;
        this.paga = false;
    }

    // getter - [retorna valor]
    public double getValor() {
        return valor;
    }

    // getter - [retorna se já foi paga]
    public boolean isPaga() {
        return paga;
    }

    // metodo - [marca a multa como paga]
    public void quitarMulta() {
        this.paga = true;
    }

    @Override
    public String toString() {
        return "Multa{valor=R$" + valor + ", paga=" + paga + '}';
    }
}
