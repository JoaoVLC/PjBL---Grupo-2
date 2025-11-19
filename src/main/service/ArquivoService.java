package main.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoService {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Salvar qualquer repositório em JSON
    public static <T> void salvar(String caminho, Repository<T> repo) {
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(repo.getAll(), writer);
            System.out.println("Arquivo salvo em: " + caminho);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar lista JSON para um repositório
    public static <T> Repository<T> carregar(String caminho, Class<T[]> tipoArray) {
        Repository<T> repo = new Repository<>();
        try (FileReader reader = new FileReader(caminho)) {

            T[] array = gson.fromJson(reader, tipoArray);
            if (array != null) {
                for (T item : array) repo.add(item);
            }

            System.out.println("Arquivo carregado de: " + caminho);

        } catch (IOException e) {
            System.out.println("Nenhum arquivo encontrado. Criando novo repositório...");
        }

        return repo;
    }
}
