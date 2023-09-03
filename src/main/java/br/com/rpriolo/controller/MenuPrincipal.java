package br.com.rpriolo.controller;

import static br.com.rpriolo.view.App.cadastro;
import static br.com.rpriolo.view.App.scanner;

public class MenuPrincipal {

    public static void mostrarMenu() {
        System.out.println("""
                \nSeja bem-vindo(a) à AGENDA!
                Escolha uma opção:
                1 - INSERIR CONTATO
                2 - CONSULTAR CONTATOS
                3 - EDITAR CONTATO
                4 - EXCLUIR CONTATO
                5 - SAIR""");
        System.out.print("OPÇÃO: ");
        escolherOpcao();
    }

    public static void escolherOpcao() {
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                cadastro.inserir();
                break;
            case 2:
                cadastro.consultar();
                break;
            case 3:
                cadastro.editar();
                break;
            case 4:
                System.out.println("""
                        Você deseja excluir o contato definitivamente ou apenas inativar?
                        1 - EXCLUIR
                        2 - INATIVAR""");
                System.out.print("OPÇÃO: ");

                int opcaoExcluir = scanner.nextInt();
                switch (opcaoExcluir) {
                    case 1:
                        cadastro.excluir();
                        break;
                    case 2:
                        cadastro.inativar();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.\n");
                        break;
                }
                break;
            case 5:
                return;
            default:
                System.out.println("Opção inválida. Tente novamente.\n");
                mostrarMenu();
        }
        mostrarMenu();
    }
}
