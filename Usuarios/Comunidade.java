package Usuarios;

import java.util.ArrayList;

public class Comunidade {
    private String nomeComunidade;
    private String descricaoComunidade;
    private Usuario administrador;
    private ArrayList<Usuario> membros;
    private ArrayList<Usuario> solicitacoesEntrada;

    public Comunidade(String nomeComunidade, String descricaoComunidade, Usuario administrador) {
        this.nomeComunidade = nomeComunidade;
        this.descricaoComunidade = descricaoComunidade;
        this.solicitacoesEntrada = new ArrayList<Usuario>();
        this.membros = new ArrayList<Usuario>();
        this.membros.add(administrador);
        this.administrador = administrador;
    }

    public String getNomeComunidade() {
        return nomeComunidade;
    }

    public void setNomeComunidade(String nomeComunidade) {
        this.nomeComunidade = nomeComunidade;
    }

    public String getDescricaoComunidade() {
        return descricaoComunidade;
    }

    public void setDescricaoComunidade(String descricaoComunidade) {
        this.descricaoComunidade = descricaoComunidade;
    }

    public Usuario getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Usuario administrador) {
        if (!membros.contains(administrador)) {
            throw new IllegalArgumentException("O administrador deve ser um membro da comunidade.\n");
        }
        this.administrador = administrador;
    }

    public ArrayList<Usuario> listarSolicitacoesEntradaComunidade() {
        return new ArrayList<Usuario>(solicitacoesEntrada);
    }

    public ArrayList<Usuario> listarMembros() {
        return new ArrayList<Usuario>(membros);
    }

    @Override
    public String toString() {
        return "Comunidade [nomeComunidade=" + nomeComunidade + ", descricaoComunidade=" + descricaoComunidade
                + ", administrador=" + administrador.getNome() + ", membros=" + membros.size()
                + ", solicitacoesEntrada=" + solicitacoesEntrada.size() + "]";
    }

    public void addMembro(Usuario usuario) {
        if (!membros.contains(usuario)) {
            membros.add(usuario);
        }
    }

    public void removeMembro(Usuario usuarioRemovido, Usuario usuarioSolicitante) {
        if (!usuarioSolicitante.equals(this.administrador)) {
            throw new IllegalArgumentException("Apenas o administrador pode remover membros da comunidade.");
        }

        if (usuarioRemovido.equals(this.administrador)) {
            throw new IllegalArgumentException("O administrador não pode ser removido da comunidade.");
        }

        membros.remove(usuarioRemovido);
    }

    public void addSolicitacaoEntrada(Usuario usuario) {
        if (membros.contains(usuario)) {
            throw new IllegalArgumentException("O usuário já é membro da comunidade.\n");
        }
        if (solicitacoesEntrada.contains(usuario)) {
            throw new IllegalArgumentException("O usuário já solicitou entrada na comunidade.\n");
        }
        solicitacoesEntrada.add(usuario);
    }

    public void removeSolicitacaoEntrada(Usuario usuario) {
        solicitacoesEntrada.remove(usuario);
    }

    public void aceitarSolicitacao(Usuario usuario) {
        if (!solicitacoesEntrada.contains(usuario) && membros.contains(usuario)) {
            throw new IllegalArgumentException("O usuário ja é membro da comunidade.\n");
        }
        if (!solicitacoesEntrada.contains(usuario)) {
            throw new IllegalArgumentException("O usuário não solicitou entrada na comunidade.\n");
        }
        addMembro(usuario);
        removeSolicitacaoEntrada(usuario);
    }

}
