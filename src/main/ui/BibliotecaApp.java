package main.ui;

import main.service.BibliotecaService;

public class BibliotecaApp {
    public static void main(String[] args) {
        BibliotecaService service = new BibliotecaService();
        Menu menu = new Menu(service);
        menu.exibirMenuPrincipal();
    }
}
