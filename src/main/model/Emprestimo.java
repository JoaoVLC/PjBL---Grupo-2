package main.model;

import java.util.Date;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private Date dataDevolucao;

    public boolean estaAtrasado() {
        return dataDevolucao != null && dataDevolucao.after(dataPrevista);
    }

    public double calcularMulta() {
        if (!estaAtrasado()) return 0;
        long diasAtraso = (dataDevolucao.getTime() - dataPrevista.getTime()) / 86400000;
        return diasAtraso * 2; // R$2 por dia atraso
    }
}
