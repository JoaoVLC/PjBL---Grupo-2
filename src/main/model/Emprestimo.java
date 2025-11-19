package main.model;

import java.util.Date;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, Date dataEmprestimo, Date dataPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevista = dataPrevista;
        this.dataDevolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean estaAtrasado() {
        return dataDevolucao != null && dataDevolucao.after(dataPrevista);
    }

    public double calcularMulta() {
        if (!estaAtrasado()) return 0;
        long diasAtraso = (dataDevolucao.getTime() - dataPrevista.getTime()) / 86400000;
        return diasAtraso * 2; // R$2 por dia atraso
    }
}
