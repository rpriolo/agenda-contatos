package br.com.rpriolo.view;

import br.com.rpriolo.controller.Cadastro;

import java.util.Scanner;

import static br.com.rpriolo.controller.MenuPrincipal.mostrarMenu;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static Cadastro cadastro = new Cadastro();

    public static void main(String[] args) {
        mostrarMenu();
        scanner.close();
    }
}
