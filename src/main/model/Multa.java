package main.model;

public class Multa {
    private double valor;
    private boolean paga;

    public Multa(double valor) {
        this.valor = valor;
        this.paga = false;
    }

    public double getValor() {
        return valor;
    }

    public boolean isPaga() {
        return paga;
    }

    public void quitarMulta() {
        this.paga = true;
    }

    @Override
    public String toString() {
        return "Multa{valor=R$" + valor + ", paga=" + paga + '}';
    }
}
