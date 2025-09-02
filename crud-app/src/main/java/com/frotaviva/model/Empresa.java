import java.util.List;

public class Empresa {
    private long id;
    private String nome;
    private Endereco endereco;
    private String email;
    private String cnpj;
    private String tipoEmpresa;
    private String senha;

    public Empresa(long id,String tipoEmpresa,String cnpj,String email,String senha,String nome,
                   Endereco endereco){
        this.id=id;
        this.nome=nome;
        this.email=email;
        this.cnpj=cnpj;
        this.endereco =endereco;
        this.tipoEmpresa=tipoEmpresa;
        this.senha=senha;
    }

    public long getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    public String getEmail(){
        return this.email;
    }
    public String getCnpj(){
        return this.cnpj;
    }
    public String getTipoEmpresa(){
        return this.tipoEmpresa;
    }
    public String getSenha(){
        return this.senha;
    }

    public String toString(){
        return "Id: "+this.id+"\nNome: "+this.nome+"\nEndereco: "+this.endereco+"\nEmail: "+this.email+
                "\nCnpj: "+this.cnpj+"\nTipo da Empresa: "+this.tipoEmpresa;
    }
}
