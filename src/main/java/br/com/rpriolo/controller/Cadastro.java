package br.com.rpriolo.controller;

import br.com.rpriolo.model.Contato;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static br.com.rpriolo.view.App.scanner;

public class Cadastro {

    private List<Contato> listaDeContatos = new ArrayList<>();
    private ConnectionFactory connection;

    public Cadastro() {
        this.connection = new ConnectionFactory();
    }

    public void inserir() {
        Connection connection = this.connection.conectar();
        Contato contato = new Contato();

        scanner.nextLine();
        System.out.print("Informe o nome: ");
        contato.setNome(scanner.nextLine());
        System.out.print("Informe o sobrenome: ");
        contato.setSobrenome(scanner.nextLine());
        System.out.print("Informe a data de nascimento: ");
        contato.setDataNascimento(scanner.nextLine());

        String nome = contato.getNome();
        String sobrenome = contato.getSobrenome();
        String dataNascimento = contato.getDataNascimento();

        new CadastroDAO(connection).salvar(nome, sobrenome, dataNascimento);
        System.out.println("Contato criado!");
    }

    public void consultar() {
        Connection connection = this.connection.conectar();

        Set<Contato> contatos = new CadastroDAO(connection).consultar();
        for (Contato contato : contatos) {
            System.out.println(contato.toString());
        }

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
        }
    }

    public void consultarPorId() {
        System.out.print("Informe o ID do contato que deseja consultar: ");
        int id = scanner.nextInt();

        Connection connection = this.connection.conectar();

        Set<Contato> contatos = new CadastroDAO(connection).consultarPorId(id);
        for (Contato contato : contatos) {
            System.out.println(contato.toString());
        }

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado com esse ID.");
        }
    }

    public void consultarPorSobrenome() {
        System.out.print("Informe o sobrenome dos contatos que deseja consultar: ");
        String sobrenome = scanner.next();

        Connection connection = this.connection.conectar();
        Set<Contato> contatos = new CadastroDAO(connection).consultarPorSobrenome(sobrenome);
        for (Contato contato : contatos) {
            System.out.println(contato.toString());
        }

        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado com esse sobrenome.");
        }
    }

    public void editar() {
        Connection connection = this.connection.conectar();
        System.out.print("Informe o ID do contato que deseja editar: ");
        int id = scanner.nextInt();

        Set<Contato> contatos = new CadastroDAO(connection).consultar();

        Contato contatoSelecionado = null;
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                contatoSelecionado = contato;
                break;
            }
        }

        if (contatoSelecionado == null) {
            System.out.println("ID não encontrado");
        } else {
            String nome = contatoSelecionado.getNome();
            String sobrenome = contatoSelecionado.getSobrenome();
            String dataNascimento = contatoSelecionado.getDataNascimento();

            System.out.println("----------------------------------------");
            System.out.println(contatoSelecionado.getId() + " | " + nome + " " + sobrenome + " | " + dataNascimento);
            System.out.println("----------------------------------------");

            System.out.println("""
                    Selecione a informação que deseja editar:
                    1 - Nome
                    2 - Sobrenome
                    3 - Data de nascimento
                    4 - Todas as informações
                    5 - Sair
                    """);
            System.out.print("OPÇÃO: ");
            int opcao = scanner.nextInt();

            String novoNome;
            String novoSobrenome;
            String novaDataNascimento;
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Informe o novo nome: ");
                    novoNome = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarNome(novoNome, contatoSelecionado.getId());
                    break;
                case 2:
                    System.out.print("Informe o novo sobrenome: ");
                    novoSobrenome = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarSobrenome(novoSobrenome, contatoSelecionado.getId());
                    break;
                case 3:
                    System.out.print("Informe a nova data de nascimento: ");
                    novaDataNascimento = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarDataNascimento(novaDataNascimento, contatoSelecionado.getId());
                    break;
                case 4:
                    System.out.print("Informe o novo nome: ");
                    novoNome = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarNome(novoNome, contatoSelecionado.getId());

                    System.out.print("Informe o novo sobrenome: ");
                    novoSobrenome = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarSobrenome(novoSobrenome, contatoSelecionado.getId());

                    System.out.print("Informe a nova data de nascimento: ");
                    novaDataNascimento = scanner.nextLine();
                    new CadastroDAO(this.connection.conectar()).editarDataNascimento(novaDataNascimento, contatoSelecionado.getId());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida.");
                    editar();
            }
            System.out.println("Contato editado com sucesso!");
        }
    }

    public void excluir() {
        System.out.print("Informe o ID do contato que deseja excluir: ");
        int id = scanner.nextInt();

        Connection connection = this.connection.conectar();
        Set<Contato> contatos = new CadastroDAO(connection).consultar();

        boolean idEncontrado = false;
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                idEncontrado = true;

                new CadastroDAO(this.connection.conectar()).excluir(id);
                System.out.println("Contato ID " + id + " excluído");
                break;
            }
        }

        if (!idEncontrado) {
            System.out.println("ID não encontrado");
        }
    }

    public void inativar() {
        System.out.print("Informe o ID do contato que deseja inativar: ");
        int id = scanner.nextInt();

        Connection conn = this.connection.conectar();
        new CadastroDAO(conn).inativar(id);
        System.out.println("Contato inativado com sucesso!");
    }
}
