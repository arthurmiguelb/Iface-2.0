import java.util.ArrayList;
import java.util.Scanner;
import Usuarios.Usuario;
import Usuarios.Comunidade;
import Usuarios.Sistema;
import Utilidade.Console;
import Utilidade.Interface;

public class Menu {
    private Sistema sistema;
    private Usuario usuarioLogado = null;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.usuarioLogado = null;
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            try {
                Interface.iface();
                Interface.inial();
                Scanner scanner = new Scanner(System.in);
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        Interface.login();
                        fazerLogin();
                        break;
                    case 2:
                        Interface.cadastro();
                        criarUsuario();
                        break;
                    case 3:
                        executando = false;
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        Console.erro("Opção inválida. Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                Console.erro(e.getMessage());
            } catch (Exception e) {
                Console.erro("Ocorreu um erro inesperado. Tente novamente.");
                Scanner scanner2 = new Scanner(System.in);
                scanner2.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
            }
        }
    }

    public void menuLogado() {
        boolean executando = true;
        while (executando) {
            try {
                Interface.limparTela();
                Interface.menuLogado(usuarioLogado);
                Scanner scanner = new Scanner(System.in);
                int opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        menuPerfil();
                        break;
                    case 2:
                        menuAmigos();
                        break;
                    case 3:
                        // comunidades();

                        break;
                    case 4:
                        // mensagens();
                        break;
                    case 5:
                        usuarioLogado = null;
                        Console.sucesso("Deslogado com sucesso.");
                        executando = false;

                        break;
                    case 0:
                        executando = false;
                        break;
                    default:
                        Console.erro("Opção inválida. Tente novamente.");
                }
            } catch (IllegalArgumentException e) {
                Console.erro(e.getMessage());
            } catch (Exception e) {
                Console.erro("Ocorreu um erro inesperado. Tente novamente.");
                Scanner scanner2 = new Scanner(System.in);
                scanner2.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
            }
        }
    }

    public void menuPerfil() {
        boolean executando = true;
        while (executando) {
            Interface.limparTela();
            Interface.meuPerfil(usuarioLogado);
            Scanner scanner = new Scanner(System.in);
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    editarPerfil();

                    break;
                case 0:
                    menuLogado();

                    break;
                default:
                    Console.erro("Opção inválida. Tente novamente.");

            }
        }
    }

    public void menuAmigos() {
        boolean executando = true;
        while (executando) {
            Interface.limparTela();
            Interface.amigos();
            Scanner scanner = new Scanner(System.in);
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    listarAmigos();

                    break;
                case 0:
                    menuLogado();
                    break;
            }
        }

    }

    public void fazerLogin() {
        Scanner scanner = new Scanner(System.in);
        Console.aviso("Digite o login do usuário:");
        String login = scanner.nextLine();
        Console.aviso("Digite a senha do usuário:");
        String senha = scanner.nextLine();
        try {
            Usuario usuario = sistema.fazLogin(login, senha);
            this.usuarioLogado = usuario;
            Console.sucesso("Login realizado com sucesso!");
            menuLogado(); // Chama o menu logado após o login bem-sucedido
        } catch (IllegalArgumentException e) {
            Console.erro(e.getMessage());
        }
    }

    public void criarUsuario() {
        Scanner scanner = new Scanner(System.in);
        Console.aviso("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        Console.aviso("Digite o login do usuário:");
        String login = scanner.nextLine();
        Console.aviso("Digite a senha do usuário:");
        String senha = scanner.nextLine();
        Console.aviso("Digite o email do usuário:");
        String email = scanner.nextLine();
        Console.aviso("Digite o número de telefone do usuário:");
        String numeroTelefone = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, login, senha, email, numeroTelefone);
        try {
            sistema.criarUsuario(novoUsuario);
            Console.sucesso("Usuário criado com sucesso!");
            this.usuarioLogado = novoUsuario; // Loga o usuário recém-criado
        } catch (IllegalArgumentException e) {
            Console.erro(e.getMessage());
        }
    }

    public void editarPerfil() {
        System.out.println("Digite novo nome:");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println("Digite novo email:");
        String email = scanner.nextLine();
        System.out.println("Digite novo telefone:");
        String telefone = scanner.nextLine();
        try {
            usuarioLogado.editarPerfil(nome, email, telefone);
            Console.sucesso("Usuário editado com sucesso!");
            System.out.println("\nPressione ENTER para continuar...");
            scanner.nextLine();

        } catch (IllegalArgumentException e) {
            Console.erro("Não foi possivel editar Perfil");
        }

    }

    public void listarAmigos() {
        ArrayList<Usuario> amigos = usuarioLogado.listarAmigos();
        Scanner scanner = new Scanner(System.in);

        if (amigos.isEmpty()) {
            Interface.limparTela();
            Console.aviso("Você não possui amigos");
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Interface.limparTela();
        System.out.println("==========MEUS AMIGOS==========");
        for (Usuario amigo : amigos) {
            System.out.println("- " + amigo.getNome() + "(" + amigo.getLogin() + ")");
        }
    }

}
