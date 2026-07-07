
import java.util.ArrayList;
import Usuarios.Usuario;
import Usuarios.Comunidade;
import Usuarios.Sistema;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema(); // iniando sistema'
        Menu menu = new Menu(sistema); // chamando menu
        menu.iniciar();

        // Usuario ADM_Comu = new Usuario("adm", "adm12", "senha1 ", "adm@example.com",
        // "111111111");
        // Comunidade comunidade = new Comunidade("Comunidade Teste", "Descrição da
        // Comunidade Teste", ADM_Comu);
        // Criando usuários + testes de amizade
        // testarCadastroUsuario(sistema);
        // testarUsuarioDuplicado(sistema);
        // testarPedidoAmizade(sistema);
        // Criando comunidade
        // testarCriacaoComunidade(sistema, ADM_Comu);
        // testarPedirParaEntrarNaComunidade(sistema);

    }

    // INICIANDO TESTES

    public static void testarCadastroUsuario(Sistema sistema) {
        // Criando usuários
        System.out.println("\nTestando cadastro de usuário...");
        Usuario usuario1 = new Usuario("Alice", "alice12", "senha1", "alice@example.com", "123456789");
        sistema.criarUsuario(usuario1);
        Usuario usuarioEncontrado = sistema.procurarUsuarioPorLogin("alice12");
        if (usuarioEncontrado != null && usuarioEncontrado.getLogin().equals("alice12")) {
            System.out.println("\nUsuário encontrado com sucesso: " + usuarioEncontrado.getNome());
        } else {
            System.out.println("\nErro: Usuário não encontrado ou nome incorreto.\n");
        }
        System.out.println("\n----------------------------------------------------\n");
    }

    public static void testarUsuarioDuplicado(Sistema sistema) {
        System.out.println("\nTestando cadastro de usuário duplicado...\n\nInserindo usuário com login já existente.");
        Usuario usuario2 = new Usuario("Alice", "alice12", "senha1", "alice@example.com", "123456789");
        try {
            sistema.criarUsuario(usuario2);
            System.out.println("\nErro: Usuário duplicado cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro esperado ao criar usuário duplicado: " + e.getMessage());
        }
        System.out.println("\nexibindo todos os usuarios cadastrados:\n");
        for (Usuario u : sistema.getUsuarios()) {
            System.out.println(" - " + u.getNome() + " (" + u.getLogin() + ")");
        }
        System.out.println("\n----------------------------------------------------\n");
    }

    public static void testarPedidoAmizade(Sistema sistema) {
        System.out.println("\nTestando pedido de amizade...\n");
        Usuario artur = new Usuario("Artur", "artur12", "senha1", "artur@example.com", "987654321");
        sistema.criarUsuario(artur);
        Usuario bob = new Usuario("Bob", "bob12", "senha1", "bob@example.com", "555555555");
        sistema.criarUsuario(bob);
        try {
            artur.enviarPedidoAmizade(bob);
            System.out.println("Pedido de amizade enviado com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao enviar pedido de amizade: \n" + e.getMessage());
        }
        System.out.print("\nTestando pedido de amizade duplicado\n");
        try {
            artur.enviarPedidoAmizade(bob);
            System.out.println("\nErro: Pedido de amizade duplicado enviado com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado ao enviar pedido de amizade duplicado: \n" + e.getMessage());
        }
        consultarPedidosAmizade(bob);
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("testando envio de amizade para si mesmo\n");
        try {
            artur.enviarPedidoAmizade(artur);
            System.out.println("\nErro: Pedido de amizade para si mesmo enviado com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado ao enviar pedido de amizade para si mesmo: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("recusando um pedido de amizade\n");
        testarRecusarPedidoAmizade(artur, bob);
        artur.enviarPedidoAmizade(bob);
        testarAceitarPedidoAmizade(artur, bob);

    }

    public static void consultarPedidosAmizade(Usuario usuario) {

        System.out.println("Consultando pedidos de amizade para " + usuario.getNome() + "...\n");
        try {
            ArrayList<Usuario> pedidos = usuario.listarPedidosAmizade();
            if (pedidos.isEmpty()) {
                System.out.println("Não há pedidos de amizade pendentes.");
            } else {
                System.out.println("Pedidos de amizade pendentes:");
                for (Usuario amigo : pedidos) {
                    System.out.println(" - " + amigo.getNome() + " (" + amigo.getLogin() + ")");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao consultar pedidos de amizade: \n" + e.getMessage());
        }

    }

    public static void testarAceitarPedidoAmizade(Usuario artur, Usuario bob) {
        System.out.println("\nTestando aceitação de pedido de amizade...\n");
        try {
            bob.aceitarPedidoAmizade(artur);
            System.out.println("Pedido de amizade aceito com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao aceitar pedido de amizade: \n" + e.getMessage());
        }
        System.out.println("\nVerificando se Artur e Bob são amigos...\n");
        ArrayList<Usuario> amigosArtur = artur.listarAmigos();
        ArrayList<Usuario> amigosBob = bob.listarAmigos();
        System.out.println("Amigos de Artur:");
        for (Usuario amigo : amigosArtur) {
            System.out.println(" - " + amigo.getNome() + " (" + amigo.getLogin() + ")");
        }
        System.out.println("\nAmigos de Bob:");
        for (Usuario amigo : amigosBob) {
            System.out.println(" - " + amigo.getNome() + " (" + amigo.getLogin() + ")");
        }
        System.out.println("\n----------------------------------------------------\n");
    }

    public static void testarRecusarPedidoAmizade(Usuario usuario1, Usuario usuario2) {
        System.out.println("\nTestando recusa de pedido de amizade...\n");
        try {
            usuario2.recusarPedidoAmizade(usuario1);
            System.out.println("Pedido de amizade recusado com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao recusar pedido de amizade: \n" + e.getMessage());
        }
        System.out.println("\nVerificando se o pedido de amizade foi removido...\n");
        ArrayList<Usuario> pedidos = usuario2.listarPedidosAmizade();
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos de amizade pendentes.");
        } else {
            System.out.println("Pedidos de amizade pendentes:");
            for (Usuario amigo : pedidos) {
                System.out.println(" - " + amigo.getNome() + " (" + amigo.getLogin() + ")");
            }
        }
        System.out.println("\n----------------------------------------------------\n");
    }

    // Inicando testes de Comunidades

    public static void testarCriacaoComunidade(Sistema sistema, Usuario administrador) {
        System.out.println("\nTestando criação de comunidade...\n");
        String nomeComunidade = "Comunidade Exemplo";
        String descricaoComunidade = "Esta é uma comunidade de exemplo.";
        try {
            sistema.criarComunidade(nomeComunidade, descricaoComunidade, administrador);
            System.out.println("Comunidade criada com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar comunidade: \n" + e.getMessage());
        }
        System.out.println("\nVerificando se a comunidade foi adicionada ao sistema...\n");
        Comunidade comunidadeEncontrada = sistema.procurarComunidadePorNome(nomeComunidade);
        if (comunidadeEncontrada != null && comunidadeEncontrada.getNomeComunidade().equals(nomeComunidade)) {
            System.out.println("Comunidade encontrada com sucesso: " + comunidadeEncontrada.getNomeComunidade());
        } else {
            System.out.println("Erro: Comunidade não encontrada ou nome incorreto.");
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("Testando criação de comunidade com mesmo nome...\n");
        try {
            sistema.criarComunidade(nomeComunidade, descricaoComunidade, administrador);
            System.out.println("Erro: Comunidade duplicada criada com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado ao criar comunidade duplicada: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");

    }

    public static void testarPedirParaEntrarNaComunidade(Sistema sistema) {
        Usuario admComu = new Usuario("adm", "adm12", "senha1 ", "adm12@example.com", "123456789");
        sistema.criarUsuario(admComu);
        Usuario usuarioSolicitante = new Usuario("Solicitante", "solicitante12", "senha1", "solicitante12@example.com",
                "123456789");
        sistema.criarUsuario(usuarioSolicitante);
        sistema.criarComunidade("Comunidade Teste", "Descrição da Comunidade Teste", admComu);
        Comunidade comunidade = sistema.procurarComunidadePorNome("Comunidade Teste");

        System.out.println("\nTestando solicitação de entrada na comunidade...\n");
        try {
            comunidade.addSolicitacaoEntrada(usuarioSolicitante);
            System.out.println("Solicitação de entrada na comunidade enviada com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao enviar solicitação de entrada na comunidade: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("listando solicitações de entrada na comunidade...\n");
        ArrayList<Usuario> solicitacoes = comunidade.listarSolicitacoesEntradaComunidade();
        for (Usuario solicitante : solicitacoes) {
            System.out.println(" - " + solicitante.getNome() + " (" + solicitante.getLogin() + ")");
        }
        testarSolicitacaoEntradaDuplicada(comunidade, usuarioSolicitante);
        testarRecusarSolicitacaoEntrada(comunidade, usuarioSolicitante);
        comunidade.addSolicitacaoEntrada(usuarioSolicitante);
        testarAceitarSolicitacaoEntrada(comunidade, usuarioSolicitante);
        testarAceitarSolicitacaoEntradaDuplicada(comunidade, usuarioSolicitante);
        testarRemoverMembroComunidade(sistema, comunidade, admComu, usuarioSolicitante);
    }

    public static void testarSolicitacaoEntradaDuplicada(Comunidade comunidade, Usuario usuarioSolicitante) {
        System.out.println("\nTestando solicitação de entrada duplicada na comunidade...\n");
        try {
            comunidade.addSolicitacaoEntrada(usuarioSolicitante);
            System.out.println("Erro: Solicitação de entrada duplicada enviada com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado ao enviar solicitação de entrada duplicada: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
    }

    public static void testarRecusarSolicitacaoEntrada(Comunidade comunidade, Usuario usuarioSolicitante) {
        System.out.println("\nTestando recusa de solicitação de entrada na comunidade...\n");
        try {
            comunidade.removeSolicitacaoEntrada(usuarioSolicitante);
            System.out.println("Solicitação de entrada na comunidade recusada com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao recusar solicitação de entrada na comunidade: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("listando solicitações de entrada na comunidade após recusa...\n");
        ArrayList<Usuario> solicitacoes = comunidade.listarSolicitacoesEntradaComunidade();
        if (solicitacoes.isEmpty()) {
            System.out.println("Não há solicitações de entrada pendentes.");
        } else {
            for (Usuario solicitante : solicitacoes) {
                System.out.println(" - " + solicitante.getNome() + " (" + solicitante.getLogin() + ")");
            }
        }
    }

    public static void testarAceitarSolicitacaoEntrada(Comunidade comunidade, Usuario usuarioSolicitante) {
        System.out.println("\nTestando aceitação de solicitação de entrada na comunidade...\n");
        try {
            comunidade.aceitarSolicitacao(usuarioSolicitante);
            System.out.println("Solicitação de entrada na comunidade aceita com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao aceitar solicitação de entrada na comunidade: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("listando membros da comunidade após aceitação...\n");
        ArrayList<Usuario> membros = comunidade.listarMembros();
        for (Usuario membro : membros) {
            System.out.println(" - " + membro.getNome() + " (" + membro.getLogin() + ")");
        }
    }

    public static void testarAceitarSolicitacaoEntradaDuplicada(Comunidade comunidade, Usuario usuarioSolicitante) {
        System.out.println("\nTestando aceitação de solicitação de entrada duplicada na comunidade...\n");
        try {
            comunidade.aceitarSolicitacao(usuarioSolicitante);
            System.out.println("Erro: Solicitação de entrada duplicada aceita com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Erro esperado ao aceitar solicitação de entrada duplicada na comunidade: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");

    }

    public static void testarRemoverMembroComunidade(Sistema sistema, Comunidade comunidade, Usuario administrador,
            Usuario usuarioRemovido) {
        System.out.println("\nTestando remoção de membro da comunidade...\n");
        try {
            comunidade.removeMembro(usuarioRemovido, administrador);
            System.out.println("Membro removido da comunidade com sucesso.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao remover membro da comunidade: \n" + e.getMessage());
        }
        System.out.println("\n----------------------------------------------------\n");
        System.out.println("listando membros da comunidade após remoção...\n");
        ArrayList<Usuario> membros = comunidade.listarMembros();
        for (Usuario membro : membros) {
            System.out.println(" - " + membro.getNome() + " (" + membro.getLogin() + ")");
        }

    }

}
