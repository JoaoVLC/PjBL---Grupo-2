//Observação: os labels "Aluno", "Professor", "LivroFisico", "LivroDigital"
// são os que aparecerão no JSON no campo "type". Não mexa neles a não ser que queira outro rótulo.

package main.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.service.RuntimeTypeAdapterFactory;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.model.Usuario;


/**
 * ArquivoService com Gson configurado para suportar polimorfismo de Usuario e Livro.
 */
public class ArquivoService {

    private static final Gson gson;

    static {
        GsonBuilder gb = new GsonBuilder().setPrettyPrinting();

        // Adapter para Usuario (Aluno/Professor)
        RuntimeTypeAdapterFactory<Usuario> usuarioFactory =
                RuntimeTypeAdapterFactory.of(Usuario.class, "type")
                        .registerSubtype(main.model.Aluno.class, "Aluno")
                        .registerSubtype(main.model.Professor.class, "Professor");

        // Adapter para Livro (LivroFisico/LivroDigital)
        RuntimeTypeAdapterFactory<main.model.Livro> livroFactory =
                RuntimeTypeAdapterFactory.of(main.model.Livro.class, "type")
                        .registerSubtype(main.model.LivroFisico.class, "LivroFisico")
                        .registerSubtype(main.model.LivroDigital.class, "LivroDigital");

        gb.registerTypeAdapterFactory(usuarioFactory);
        gb.registerTypeAdapterFactory(livroFactory);

        gson = gb.create();
    }

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

