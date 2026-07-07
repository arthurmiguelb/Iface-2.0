package Usuarios;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Comunidade> comunidades;

    public Sistema() {
        this.usuarios = new ArrayList<Usuario>();
        this.comunidades = new ArrayList<Comunidade>();
    }

    @Override
    public String toString() {
        return "Sistema [usuarios=" + usuarios.size() + ", comunidades=" + comunidades.size() + "]";
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Comunidade> getComunidades() {
        return comunidades;
    }

    public Usuario procurarUsuarioPorLogin(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public Usuario fazLogin(String login, String senha) {
        Usuario usuario = procurarUsuarioPorLogin(login);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.\n");
        }
        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.\n");
        }
        return usuario;
    }

    public void criarUsuario(Usuario usuario) {
        if (procurarUsuarioPorLogin(usuario.getLogin()) != null) {
            throw new IllegalArgumentException("Já existe um usuário com esse login.\n");
        }
        usuarios.add(usuario);

    }

    public void criarComunidade(String nomeComunidade, String descricaoComunidade, Usuario administrador) {
        if (procurarComunidadePorNome(nomeComunidade) != null) {
            throw new IllegalArgumentException("Já existe uma comunidade com esse nome.\n");
        }
        Comunidade novaComunidade = new Comunidade(nomeComunidade, descricaoComunidade, administrador);
        comunidades.add(novaComunidade);
        administrador.adicionarComunidade(novaComunidade);
    }

    public Comunidade procurarComunidadePorNome(String nomeComunidade) {
        for (Comunidade comunidade : comunidades) {
            if (comunidade.getNomeComunidade().equals(nomeComunidade)) {
                return comunidade;
            }
        }
        return null; // Retorna null se a comunidade não for encontrada
    }

    public void removerUsuario(Usuario usuarioRemovido, Usuario usuarioSolicitante) {
        if (!usuarios.contains(usuarioRemovido)) {
            return;
        }

        for (Comunidade comunidade : comunidades) {
            if (comunidade.getAdministrador().equals(usuarioSolicitante)) {
                throw new IllegalArgumentException(
                        "Não é possível remover um usuário que administra uma comunidade.\n");
            }
        }

        usuarios.remove(usuarioRemovido);

        for (Comunidade comunidade : comunidades) {
            comunidade.removeMembro(usuarioRemovido, usuarioSolicitante);
            comunidade.removeSolicitacaoEntrada(usuarioRemovido);
        }
    }

    public void removerComunidade(Comunidade comunidade, Usuario usuarioSolicitante) {
        if (!comunidades.contains(comunidade)) {
            throw new IllegalArgumentException("Comunidade não existe no sistema.\n");
        }

        if (!comunidade.getAdministrador().equals(usuarioSolicitante)) {
            throw new IllegalArgumentException("Apenas o administrador pode remover a comunidade.\n");
        }

        comunidades.remove(comunidade);

        for (Usuario usuario : usuarios) {
            usuario.removerListaComunidade(comunidade);
        }
    }

}
