package entidade;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String celular;
    private String cpf;
    private String senha;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;

    public Aluno() {
        this.id = 0;
        this.nome = "";
        this.email = "";
        this.celular = "";
        this.cpf = "";
        this.senha = "";
        this.endereco = "";
        this.cidade = "";
        this.bairro = "";
        this.cep = "";
    }

    public Aluno(int id, String nome, String email, String celular, String cpf, String senha, String endereco,
            String cidade, String bairro, String cep) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
    }
    public Aluno (String nome, String email, String celular, String cpf, String senha, String endereco,
    String cidade, String bairro, String cep) {
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Aluno(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
