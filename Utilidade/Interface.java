package Utilidade;

import Usuarios.Usuario;

public class Interface {
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        Interface.usuarioLogado = usuario;
    }

    public static void iface() {
        System.out.println("""
                +------------------------------------------------------+
                |                                                      |
                |   ██╗███████╗ █████╗  ██████╗███████╗                |
                |   ██║██╔════╝██╔══██╗██╔════╝██╔════╝                |
                |   ██║█████╗  ███████║██║     █████╗                  |
                |   ██║██╔══╝  ██╔══██║██║     ██╔══╝                  |
                |   ██║██║     ██║  ██║╚██████╗███████╗                |
                |   ╚═╝╚═╝     ╚═╝  ╚═╝ ╚═════╝╚══════╝                |
                |                                                      |
                |             Rede Social em Java - v1.0               |
                |                                                      |
                +------------------------------------------------------+
                """);
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void inial() {
        System.out.println("Bem-vindo ao Sistema!");
        System.out.println("[1] Fazer Login");
        System.out.println("[2] Criar usuário");
        System.out.println("[3] Sair");
    }

    public static void menuLogado(Usuario usuarioLogado) {
        System.out.println("========================================================");
        System.out.println("                       IFACE");
        System.out.println("========================================================");
        System.out.println();
        System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
        System.out.println("Login: " + usuarioLogado.getLogin());
        System.out.println();
        System.out.println("========================================================");
        System.out.println("                  MENU PRINCIPAL");
        System.out.println("========================================================");
        System.out.println("[1] Meu Perfil");
        System.out.println("[2] Amigos");
        System.out.println("[3] Comunidades");
        System.out.println("[4] Mensagens");
        System.out.println("[5] Logout");
        System.out.println();
        System.out.println("[0] Encerrar Sistema");
        System.out.println("========================================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void login() {
        limparTela();
        System.out.println("========================================================");
        System.out.println("                     LOGIN DE USUÁRIO");
        System.out.println("========================================================");
    }

    public static void cadastro() {
        limparTela();
        System.out.println("========================================================");
        System.out.println("                  CADASTRO DE USUÁRIO");
        System.out.println("========================================================");
    }

    public static void meuPerfil(Usuario usuarioLogado) {
        System.out.println("========================================================");
        System.out.println("                     MEU PERFIL");
        System.out.println("========================================================");
        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Login: " + usuarioLogado.getLogin());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Telefone: " + usuarioLogado.getNumeroTelefone());
        System.out.println("========================================================");
        System.out.println("[1] Editar Perfil");
        System.out.println("[0] Voltar");
    }

    public static void amigos(){
        System.out.println("========================================================");
        System.out.println("                          AMIGOS");
        System.out.println("========================================================");
        System.out.println("[1] Listar meu amigos");
        System.out.println("[2] Enviar solicitação de amizade");
        System.out.println("[3] Listar solicitações pendentes");
        System.out.println("[4] Aceitar Solicitação");
        System.out.println("[5] Recusar Solicitação");

        System.out.println("[0] Voltar");

    }

    public static void menuComunidade() {
        System.out.println("========================================================");
        System.out.println("                     COMUNIDADES");
        System.out.println("========================================================");
        System.out.println("[1] Listar minhas comunidades");
        System.out.println("[2] Criar Comunidade");

        System.out.println("[0] Voltar");


    }
}
