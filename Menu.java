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
                Interface.limparTela();
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
                        menuComunidades();

                        break;
                    case 4:
                        menuMensagens();
                        break;
                    case 5:
                        usuarioLogado = null;
                        Scanner scanner2 = new Scanner(System.in);
                        Interface.limparTela();
                        Console.sucesso("Deslogado com sucesso.\n");
                        Console.enterContinue();
                        scanner2.nextLine();
                        iniciar();

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
                case 2:
                    enviarAmizade();

                    break;
                case 3:
                    listarSolicitacoes();

                    break;

                case 4:
                    aceitarSolicitacoes();

                    break;

                case 5:
                    recusarSolicitacoes();

                    break;
                case 0:
                    menuLogado();
                    break;
            }
        }

    }

    public void menuComunidades() {
        boolean executando = true;
        while (executando) {
            Interface.limparTela();
            Interface.menuComunidade();
            Scanner scan = new Scanner(System.in);
            int opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    listarComunidades();
                    break;
                case 2:
                    criarComunidade();
                    break;
                case 3:
                    administrarComunidade();

                    break;
                case 4:
                    solicitaComunidade();

                    break;
                case 5:
                    return;

            }
        }

    }

    public void menuAdmComu(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        Interface.limparTela();
        System.out.println("========================================================");
        System.out.println("Comunidade: " + selecionada.getNomeComunidade());
        System.out.println("========================================================");
        Interface.menuAdmComunidade();
        int opcao = scan.nextInt();
        switch (opcao) {
            case 1:
                listarMembrosComunidade(selecionada);
                break;
            case 2:
                aceitarMembrosComunidade(selecionada);
                break;
            case 3:
                recusarSoliciMembrosComunidade(selecionada);
                break;
            case 4:
                removerMembroComunidade(selecionada);
                break;
            case 5:
                excluirComunidade(selecionada);
                break;

            default:
                break;
        }
    }

    public void menuMensagens() {
        Interface.limparTela();
        Interface.menuMensagens();
        Scanner scan = new Scanner(System.in);
        int opt = scan.nextInt();
        switch (opt) {
            case 1:
                exibirMensagens();
                break;
            case 2:
                enviarMensagens();
                break;

            default:
                break;
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
            Console.enterContinue();
            scanner.nextLine();
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
            Console.enterContinue();
            scanner.nextLine();
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
            Console.enterContinue();
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
            System.out.println("- " + amigo.getNome() + " (" + amigo.getLogin() + ")");
        }
        Console.enterContinue();
        scanner.nextLine();
        return;
    }

    public void enviarAmizade() {
        ArrayList<Usuario> amigos = usuarioLogado.listarAmigos();
        Scanner scanner = new Scanner(System.in);
        Interface.limparTela();
        System.out.println("\n");
        System.out.println("Digite o Login do usuário que deseja enviar solicitação de amizade:");
        String nome_amigo = scanner.nextLine();
        Usuario amigo = sistema.procurarUsuarioPorLogin(nome_amigo);
        if (amigo != null) {
            try {

                usuarioLogado.enviarPedidoAmizade(amigo);
                Interface.limparTela();
                Console.sucesso("Pedido de amizade enviado!");
                scanner.nextLine();
                Console.enterContinue();
            } catch (IllegalArgumentException e) {
                Console.erro(e.getMessage());
                scanner.nextLine();
                Console.enterContinue();
            }
        } else {
            Interface.limparTela();
            Console.erro("Usuário não encontrado");
            Console.enterContinue();
            scanner.nextLine();
            menuAmigos();
        }
    }

    public void listarSolicitacoes() {
        ArrayList<Usuario> solici = usuarioLogado.listarPedidosAmizade();
        Scanner scanner = new Scanner(System.in);
        if (solici.isEmpty()) {
            Interface.limparTela();
            Console.aviso("Você não possui Pedidos");
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Interface.limparTela();
        Interface.minhasSolicitacoes();
        for (Usuario solicita : solici) {
            System.out.println("Nome: " + solicita.getNome());
            System.out.println("Login: " + solicita.getLogin());
            System.out.println("-------------------------------");

        }
        Console.enterContinue();
        scanner.nextLine();
        return;
    }

    public void aceitarSolicitacoes() {
        ArrayList<Usuario> solictList = usuarioLogado.listarPedidosAmizade();
        Scanner scanner = new Scanner(System.in);

        if (solictList.isEmpty()) {
            Interface.limparTela();
            Console.aviso("Você não possui pedidos de amizade\n");
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Interface.limparTela();
        Interface.minhasSolicitacoes();
        System.out.println("Solicitações pendentes:\n");
        for (Usuario solicitacoes : solictList) {
            System.out.println("Nome: " + solicitacoes.getNome());
            System.out.println("Login: " + solicitacoes.getLogin());
            System.out.println("-------------------------------");
        }
        System.out.println("\n");
        System.out.println("Digite o Login do usuário que deseja aceitar:");
        String login = scanner.nextLine();
        Usuario login1 = sistema.procurarUsuarioPorLogin(login);
        usuarioLogado.aceitarPedidoAmizade(login1);
    }

    public void recusarSolicitacoes() {
        ArrayList<Usuario> solictList = usuarioLogado.listarPedidosAmizade();
        Scanner scanner = new Scanner(System.in);

        if (solictList.isEmpty()) {
            Interface.limparTela();
            Console.aviso("Você não possui pedidos de amizade\n");
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Interface.limparTela();
        System.out.println("Solicitações pendentes:\n");
        for (Usuario solicitacoes : solictList) {
            System.out.println("Nome: " + solicitacoes.getNome());
            System.out.println("Login: " + solicitacoes.getLogin());
            System.out.println("-------------------------------");

        }
        System.out.println("\n");
        System.out.println("Digite o Login do usuário que deseja recusar:");
        String login = scanner.nextLine();
        Usuario login1 = sistema.procurarUsuarioPorLogin(login);
        usuarioLogado.recusarPedidoAmizade(login1);
    }

    public void listarComunidades() {
        ArrayList<Comunidade> comunidades = usuarioLogado.listaMinhasComunidades();
        Scanner scanner = new Scanner(System.in);
        if (comunidades.isEmpty()) {
            Interface.limparTela();
            Console.aviso("Você não possui Comunidades");
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Interface.limparTela();
        Interface.comunidadeGeral();
        for (Comunidade comuinidadess : comunidades) {
            System.out.println("Nome: " + comuinidadess.getNomeComunidade());
            System.out.println("Descrição: " + comuinidadess.getDescricaoComunidade());
            System.out.println("-------------------------------");

        }
        Console.enterContinue();
        scanner.nextLine();
        return;
    }

    public void criarComunidade() {
        Scanner scan = new Scanner(System.in);
        String nomeComu;
        String descComu;
        Interface.limparTela();
        System.out.println("Digite o nome da comunidade:");
        nomeComu = scan.nextLine();
        System.out.println("Digite a descrição da comunidade:");
        descComu = scan.nextLine();
        try {
            Interface.limparTela();
            sistema.criarComunidade(nomeComu, descComu, usuarioLogado);
            Console.sucesso("Comunidade criada com sucesso!");
            Console.enterContinue();
            scan.nextLine();
        } catch (IllegalArgumentException e) {
            Console.erro(e.getMessage());
            Console.enterContinue();
            scan.nextLine();
        }
    }

    public void solicitaComunidade() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Comunidade> todasComunidades = sistema.listaComunidades();
        Interface.limparTela();
        System.out.println("Todas comunidades disponíveis:\n");
        System.out.println("-------------------------------");
        for (Comunidade comunidaes : todasComunidades) {
            System.out.println("Nome: " + comunidaes.getNomeComunidade());
            System.out.println("descrição: " + comunidaes.getNomeComunidade());
        }
        System.out.println("-------------------------------\n");
        System.out.println("Digite o nome da comunidade que deseja solicitar entrada:");
        String nomeComunidade = scanner.nextLine();
        Comunidade comunidade = sistema.procurarComunidadePorNome(nomeComunidade);
        try {
            if (comunidade != null) {
                comunidade.addSolicitacaoEntrada(usuarioLogado);
                Interface.limparTela();
                Console.sucesso("Solicitação enviada com sucesso!");
                Console.enterContinue();
                scanner.nextLine();
                return;
            }
        } catch (IllegalArgumentException e) {
            Console.erro(e.getMessage());
            Console.enterContinue();
            scanner.nextLine();
            return;
        }
        Console.erro("Comunidade não encontrada!");
        Console.enterContinue();
        scanner.nextLine();
        return;
    }

    public void administrarComunidade() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Comunidade> comunidadesAdm = sistema.listaComunidadesDoAdm(usuarioLogado);
        Comunidade selecionada;
        if (comunidadesAdm.isEmpty()) {
            Interface.limparTela();
            Console.erro("Você não administra nenhuma comunidade");
            Console.enterContinue();
            scan.nextLine();
            return;
        }
        Interface.limparTela();
        Interface.comunidadeGeral();
        System.out.println("Selecione a comunidade que deseja administrar: \n");
        for (int i = 0; i < comunidadesAdm.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + comunidadesAdm.get(i).getNomeComunidade());
        }
        int opcao = scan.nextInt() - 1;
        selecionada = comunidadesAdm.get(opcao);
        menuAdmComu(selecionada);
    }

    public void listarMembrosComunidade(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> membros;
        membros = selecionada.listarMembros();
        Interface.limparTela();
        System.out.println("========================================================");
        System.out.println("Comunidade: " + selecionada.getNomeComunidade());
        System.out.println("========================================================\n");
        System.out.println("Membros da comunidade:");
        for (Usuario membro : membros) {
            System.out.println("--------------------------------");
            System.out.println("Nome: " + membro.getNome());
            System.out.println("Login: " + membro.getLogin());
            System.out.println("--------------------------------");
        }
        Console.enterContinue();
        scan.nextLine();
        menuAdmComu(selecionada);
    }

    public void aceitarMembrosComunidade(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> solicita = selecionada.listarSolicitacoesEntradaComunidade();
        if (!solicita.isEmpty()) {
            Interface.limparTela();
            System.out.println("========================================================");
            System.out.println("Comunidade: " + selecionada.getNomeComunidade());
            System.out.println("========================================================\n");
            System.out.println("Solicitações comunidade:");
            for (Usuario membro : solicita) {
                System.out.println("--------------------------------");
                System.out.println("Nome: " + membro.getNome());
                System.out.println("Login: " + membro.getLogin());
                System.out.println("--------------------------------");
            }
            System.out.println("Digite o login do usuário que deseja aceitar: ");
            String selecionado = scan.nextLine();
            Usuario oSelecionado = sistema.procurarUsuarioPorLogin(selecionado);
            try {
                selecionada.aceitarSolicitacao(usuarioLogado, oSelecionado);
                Console.sucesso("Usuário aceito na comunidade!");
                Console.enterContinue();
                scan.nextLine();
                menuAdmComu(selecionada);
                return;

            } catch (IllegalArgumentException e) {
                Console.erro(e.getMessage());
                Console.enterContinue();
                scan.nextLine();
                menuAdmComu(selecionada);
                return;
            }

        } else if (solicita.isEmpty()) {
            Interface.limparTela();
            Console.aviso("A comunidade não possui solicitações!");
            Console.enterContinue();
            scan.nextLine();
            menuAdmComu(selecionada);
        }

    }

    public void recusarSoliciMembrosComunidade(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> solicita = selecionada.listarSolicitacoesEntradaComunidade();
        if (!solicita.isEmpty()) {
            Interface.limparTela();
            System.out.println("========================================================");
            System.out.println("Comunidade: " + selecionada.getNomeComunidade());
            System.out.println("========================================================\n");
            System.out.println("Solicitações comunidade:");
            for (Usuario membro : solicita) {
                System.out.println("--------------------------------");
                System.out.println("Nome: " + membro.getNome());
                System.out.println("Login: " + membro.getLogin());
                System.out.println("--------------------------------");
            }
            System.out.println("Digite o login do usuário que deseja recusar: ");
            String selecionado = scan.nextLine();
            Usuario oSelecionado = sistema.procurarUsuarioPorLogin(selecionado);
            try {
                selecionada.removeSolicitacaoEntrada(oSelecionado);
                Console.sucesso("Usuário rejeitado na comunidade!");
                Console.enterContinue();
                scan.nextLine();
                menuAdmComu(selecionada);
                return;

            } catch (IllegalArgumentException e) {
                Console.erro(e.getMessage());
                Console.enterContinue();
                scan.nextLine();
                menuAdmComu(selecionada);
                return;
            }

        } else if (solicita.isEmpty()) {
            Interface.limparTela();
            Console.aviso("A comunidade não possui solicitações!");
            Console.enterContinue();
            scan.nextLine();
            menuAdmComu(selecionada);
        }
    }

    public void removerMembroComunidade(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> membros;
        membros = selecionada.listarMembros();
        Interface.limparTela();
        System.out.println("========================================================");
        System.out.println("Comunidade: " + selecionada.getNomeComunidade());
        System.out.println("========================================================\n");
        System.out.println("Membros da comunidade:");
        for (Usuario membro : membros) {
            System.out.println("--------------------------------");
            System.out.println("Nome: " + membro.getNome());
            System.out.println("Login: " + membro.getLogin());
            System.out.println("--------------------------------");
        }
        System.out.println("Digite o login do usuário que deseja remover da comunidade: ");
        String usuarioSelec = scan.nextLine();
        Usuario selecionado = sistema.procurarUsuarioPorLogin(usuarioSelec);
        try {
            Interface.limparTela();
            selecionada.removeMembro(selecionado, usuarioLogado);
            Console.sucesso("Usuário removido da comunidade!");
            Console.enterContinue();
            scan.nextLine();
            menuAdmComu(selecionada);
            return;
        } catch (IllegalArgumentException e) {
            Console.erro(e.getMessage());
            Console.enterContinue();
            scan.nextLine();
            menuAdmComu(selecionada);
            return;
        }
    }

    public void excluirComunidade(Comunidade selecionada) {
        Scanner scan = new Scanner(System.in);
        Scanner scann = new Scanner(System.in);

        Interface.limparTela();
        System.out.println("Certeza que deseja excluir comunidade?");
        System.out.println("[1] Sim");
        System.out.println("[2] Não");
        int opt = scan.nextInt();
        if (opt == 1) {
            Interface.limparTela();
            sistema.removerComunidade(selecionada, usuarioLogado);
            Console.sucesso("Comnunidade excluida com sucesso!");
            Console.enterContinue();
            scann.nextLine();
            menuComunidades();
        }
        menuAdmComu(selecionada);
        return;
    }

    public void enviarMensagens() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Usuario> amigos = usuarioLogado.listarAmigos();
        Interface.limparTela();
        if (!amigos.isEmpty()) {
            System.out.println("Listando todos os seus amigos\n");
            System.out.println("--------------------------------");
            for (Usuario amigo : amigos) {
                System.out.println("Nome: " + amigo.getNome());
                System.out.println("Login: " + amigo.getLogin());
            }
            System.out.println("--------------------------------\n");
            System.out.println("Digite o login do amigo que deseja enviar mensagem:");
            String amigo = scan.nextLine();
            Usuario amigoM = usuarioLogado.procurarAmigoPorLogin(amigo);
            if (amigoM != null) {
                Interface.limparTela();
                System.out.println("Digite a mensagem que deseja Enviar: ");
                String mensagem = scan.nextLine();
                amigoM.receberMensagem("- " + usuarioLogado.getNome() + ": " + mensagem);
                Console.sucesso("Mensagem enviada com sucesso!");
                Console.enterContinue();
                scan.nextLine();
                return;
            } else if (amigoM == null) {
                Console.erro("Amigo não encontrado!");
                Console.enterContinue();
                scan.nextLine();
                return;
            }
        }
    }

    public void exibirMensagens() {
        ArrayList<String> mensagens = usuarioLogado.listaMinhasMensagens();
        Scanner scan = new Scanner(System.in);
        Interface.limparTela();
        if (!mensagens.isEmpty()) {
            for (String mensagem : mensagens) {
                System.out.println(mensagem);
            }
            Console.enterContinue();
            scan.nextLine();
            return;
        }
        Console.aviso("Você não possui mensagens!");
        Console.enterContinue();
        scan.nextLine();
    }
}
