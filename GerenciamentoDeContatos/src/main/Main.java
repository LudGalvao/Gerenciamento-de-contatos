package main;

import dao.ContatoDAO;
import modelo.Contato;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final ContatoDAO contatoDAO;
    private final Scanner scanner;

    public Main() {
        this.contatoDAO = new ContatoDAO();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int opcao;
        do {
            exibirMenu();
            opcao = obterOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Inserir novo contato");
        System.out.println("2. Listar contatos");
        System.out.println("3. Listar contatos por letra inicial do nome");
        System.out.println("4. Buscar contato por ID");
        System.out.println("5. Remover contato por ID");
        System.out.println("0. Sair");
    }

    private int obterOpcao() {
        System.out.print("Digite sua escolha: ");
        return scanner.nextInt();
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                inserirContato();
                break;
            case 2:
                listarContatos();
                break;
            case 3:
                listarContatosPorLetra();
                break;
            case 4:
                buscarContatoPorId();
                break;
            case 5:
                removerContatoPorId();
                break;
            case 0:
                System.out.println("Encerrando o programa...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void inserirContato() {
        // Lógica para inserir um novo contato
        System.out.println("Executando ação: Inserir novo contato");
//        System.out.println("Digite o ID: ");
//        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Digite o nome do contato:");
        String nome = scanner.nextLine();
        System.out.println("Digite o email do contato:");
        String email = scanner.nextLine();
        System.out.println("Digite o endereço do contato:");
        String endereco = scanner.nextLine();

        Contato novoContato = new Contato();
//        novoContato.setId(id);
        novoContato.setNome(nome);
        novoContato.setEmail(email);
        novoContato.setEndereco(endereco);

        contatoDAO.inserirContato(novoContato);
        System.out.println("Contato inserido com sucesso!");
    }

    private void listarContatos() {
        // Lógica para listar contatos
        System.out.println("Executando ação: Listar contatos");
        List<Contato> contatos = contatoDAO.listarContatos();
        exibirContatos(contatos);
    }

    private void listarContatosPorLetra() {
        // Lógica para listar contatos por letra inicial
        System.out.println("Executando ação: Listar contatos por letra inicial do nome");
        System.out.println("Digite a letra inicial para listar contatos:");
        char letra = scanner.next().charAt(0);
        List<Contato> contatos = contatoDAO.listarContatosPorLetraInicial(letra);
        exibirContatos(contatos);
    }

    private void buscarContatoPorId() {
        // Lógica para buscar contato por ID
        System.out.println("Executando ação: Buscar contato por ID");
        System.out.println("Digite o ID do contato a ser buscado:");
        int id = scanner.nextInt();
        Contato contato = contatoDAO.buscarContatoPorId(id);
        if (contato != null) {
            System.out.println("\nContato Encontrado:");
            exibirContato(contato);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private void exibirContatos(List<Contato> contatos) {
        System.out.println("\nLista de Contatos:");
        for (Contato contato : contatos) {
            exibirContato(contato);
        }
    }

    private void exibirContato(Contato contato) {
        System.out.println(contato.getId() + " - " + contato.getNome() + " - " + contato.getEmail() + " - " + contato.getEndereco());
    }

    private void removerContatoPorId() {
        System.out.println("Executando ação: Remover contato por ID");
        System.out.println("Digite o ID do contato a ser removido:");
        int id = scanner.nextInt();

        contatoDAO.removerContatoPorId(id);

        System.out.println("Contato removido com sucesso!");
    }


    public static void main(String[] args) {
        new Main().start();
    }
}