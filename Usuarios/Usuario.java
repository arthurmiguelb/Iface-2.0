package Usuarios;

import java.util.ArrayList;

public class Usuario {
    private ArrayList<Usuario> amigos;
    private ArrayList<String> mensagens;
    private ArrayList<Usuario> pedidosAmizade;
    private ArrayList<Comunidade> comunidades;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String numeroTelefone;

    public Usuario(String nome, String login, String senha, String email, String numeroTelefone) {
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.amigos = new ArrayList<Usuario>();
        this.pedidosAmizade = new ArrayList<Usuario>();
        this.comunidades = new ArrayList<Comunidade>();
        this.mensagens = new ArrayList<String>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "\nnome: " + getNome() +
                "\nlogin: " + getLogin() +
                "\nemail: " + getEmail() +
                "\ntelefone: " + getNumeroTelefone() +
                // "\npassword: " + this.password+
                "\n";
    }

    public void adicionarAmigo(Usuario amigo) {
        if (amigos.contains(amigo)) {
            throw new IllegalArgumentException("O usuário já é amigo.\n");
        }
        if (amigo.equals(this)) {
            throw new IllegalArgumentException("O usuário não pode adicionar a si mesmo como amigo.\n");
        }
        amigos.add(amigo);
    }

    public void aceitarPedidoAmizade(Usuario amigo) {
        if (!pedidosAmizade.contains(amigo)) {
            throw new IllegalArgumentException("O usuário não solicitou amizade.\n");
        }
        this.adicionarAmigo(amigo);
        amigo.adicionarAmigo(this);

        pedidosAmizade.remove(amigo);
    }

    public void enviarPedidoAmizade(Usuario amigo) {
        if (amigo.equals(this)) {
            throw new IllegalArgumentException("O usuário não pode enviar pedido de amizade para si mesmo.\n");
        }
        if (amigos.contains(amigo)) {
            throw new IllegalArgumentException("O usuário já é amigo.\n");
        }
        if (amigo.pedidosAmizade.contains(this)) {
            throw new IllegalArgumentException("Você ja solicitou amizade.\n");
        }
        amigo.pedidosAmizade.add(this);
    }

    public void recusarPedidoAmizade(Usuario amigo) {
        if (!pedidosAmizade.contains(amigo)) {
            throw new IllegalArgumentException("O usuário não solicitou amizade.\n");
        }
        pedidosAmizade.remove(amigo);
    }

    public ArrayList<Usuario> listarPedidosAmizade() {
        return new ArrayList<Usuario>(pedidosAmizade);
    }

    public ArrayList<Usuario> listarAmigos() {
        return new ArrayList<Usuario>(amigos);
    }

    public Usuario editarPerfil(String nome, String email, String numeroTelefone) {
        this.nome = nome;
        this.email = email;
        this.numeroTelefone = numeroTelefone;
        return this;
        
    }

    public void adicionarComunidade(Comunidade comunidade) {
        if (!comunidades.contains(comunidade)) {
            comunidades.add(comunidade);
        }
    }

    public void removerListaComunidade(Comunidade comunidade) {
        comunidades.remove(comunidade);
    }
}
