package br.com.rpriolo.model;

public class Contato {
    private int id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private boolean estaAtivo;

    public Contato() {

    }

    public Contato(int id, String nome, String sobrenome, String dataNascimento, boolean estaAtivo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.estaAtivo = estaAtivo;
    }

    @Override
    public String toString() {
        if (dataNascimento.isEmpty()) {
            return "ID: " + id +
                    " | " + nome + " " + sobrenome;
        } else {
            return "ID: " + id +
                    " | " + nome + " " + sobrenome +
                    " (" + dataNascimento + ")";
        }
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean getEstaAtivo() {
        return estaAtivo;
    }
}
