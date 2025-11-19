package main.ui;

import main.service.BibliotecaService;
import main.ui.swing.TelaPrincipal;

public class BibliotecaApp {
    public static void main(String[] args) {

        // Inicializa o serviço
        BibliotecaService service = new BibliotecaService();

        // Abre a tela principal da interface gráfica
        new TelaPrincipal(service);
    }
}
