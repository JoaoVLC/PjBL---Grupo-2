package main.service;

import main.model.*;
import java.io.*;
import java.util.List;

/*
 * ArquivoTxtService
 * - Serviço utilitário para salvar / carregar listas em arquivos .txt simples (CSV-like).
 * - Cada metodo é estático: passar caminho e dados / serviço para reconstruir objetos via BibliotecaService.
 * Métodos:
 * - salvarAutores(caminho, autores): grava cada autor em uma linha "id,nomeCompleto,nacionalidade"
 * - carregarAutores(caminho, service): lê linhas e chama service.cadastrarAutor(...) para recriar autores
 * - salvarUsuarios / carregarUsuarios: formata "TIPO,NOME,ID" e reconstrói via service.cadastrarUsuario(...)
 * - salvarLivros / carregarLivros: formata "TIPO,TITULO,ID_AUTOR,ISBN,DISPONIVEL"
 * Observação: os métodos usam service.cadastrar... para garantir consistência com regras do sistema.
 */

public class ArquivoTxtService {


    // metodo - [salva lista de autores em arquivo CSV simples]
    public static void salvarAutores(String caminho, List<Autor> autores) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (Autor a : autores) {
                bw.write(a.getId() + "," +
                        a.getNomeCompleto() + "," +
                        a.getNacionalidade());
                bw.newLine();
            }
        }
    }

    // metodo - [carrega autores e usa service.cadastrarAutor para integrar]
    public static void carregarAutores(String caminho, BibliotecaService service) throws Exception {

        File file = new File(caminho);
        if (!file.exists()) return; // Se o arquivo não existe, não faz nada

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                // formato: id,nomeCompleto,nacionalidade
                String nomeCompleto = dados[1].trim();
                String[] nome = nomeCompleto.split(" ");

                service.cadastrarAutor(
                        nome[0],                       // nome
                        nome.length > 1 ? nome[1] : "",// sobrenome
                        dados[2].trim()                // nacionalidade
                );
            }
        }
    }

    // metodo - [salva usuarios em formato TIPONOME,ID]
    public static void salvarUsuarios(String caminho, List<Usuario> usuarios) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (Usuario u : usuarios) {

                String tipo = (u instanceof Aluno) ? "ALUNO" : "PROFESSOR";

                bw.write(tipo + "," +
                        u.getNome() + "," +
                        u.getId());
                bw.newLine();
            }
        }
    }

    // metodo - [carrega usuarios e chama service.cadastrarUsuario()]
    public static void carregarUsuarios(String caminho, BibliotecaService service) throws Exception {

        File file = new File(caminho);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(",");
                String tipo = dados[0].trim();
                String nome = dados[1].trim();
                String id = dados[2].trim();

                service.cadastrarUsuario(nome, id, tipo.toLowerCase());
            }
        }
    }

    // metodo - [salva livros com informações mínimas; usa idAutor para linkar]
    public static void salvarLivros(String caminho, List<Livro> livros) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            for (Livro l : livros) {

                String tipo = (l instanceof LivroFisico) ? "FISICO" : "DIGITAL";

                bw.write(tipo + "," +
                        l.getTitulo() + "," +
                        l.getAutor().getId() + "," +
                        l.getIsbn() + "," +
                        l.getDisponivel());
                bw.newLine();
            }
        }
    }

    // metodo - [carrega livros; recria via service.cadastrarLivro()]
    public static void carregarLivros(String caminho, BibliotecaService service) throws Exception {

        File file = new File(caminho);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(",");

                String tipo = dados[0].trim();
                String titulo = dados[1].trim();
                int idAutor = Integer.parseInt(dados[2].trim());
                String isbn = dados[3].trim();

                Autor autor = service.buscarAutorPorId(idAutor);
                if (autor == null) continue;

                if (tipo.equalsIgnoreCase("FISICO")) {
                    service.cadastrarLivro(titulo, idAutor, isbn, "fisico");
                } else {
                    service.cadastrarLivro(titulo, idAutor, isbn, "digital");
                }
            }
        }
    }

}
