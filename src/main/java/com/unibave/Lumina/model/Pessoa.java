package com.unibave.Lumina.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public abstract class Pessoa {

    @Column(name = "nome", nullable = false, unique = false)
    protected String nome;
    @Column(name = "situacao", nullable = false, unique = false)
    protected byte situacao;
    @Column(name = "dt_cadastro", nullable = false, unique = false, updatable = false)
    protected LocalDate dtCadastro;

    //Constructors

    public Pessoa (){
        this.situacao = 1;
        this.dtCadastro = LocalDate.now();
    }
    public Pessoa(String nome) {
        this.nome = nome;
        this.situacao = 1;
        this.dtCadastro = LocalDate.now();
    }

    //Methods
    @Override
    public String toString() {
        return "Nome " + nome + ", data de cadastro " + dtCadastro;
    }

    //Getter & Setter
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }

        if (nome.length() > 255) { // Limite arbitrário, ajuste conforme necessidade
            throw new IllegalArgumentException("Nome não pode ter mais que 255 caracteres");
        }
        // Verificar se contém apenas caracteres válidos
        if (!nome.matches("[a-zA-ZÀ-ÿ\\s.-]+")) {
            throw new IllegalArgumentException("Caracter inválido.");
        }
        this.nome = nome.trim();
    }

    public byte getSituacao() {
        return situacao;
    }

    public void setSituacao(byte situacao) {
        if (!String.valueOf(situacao).matches("[0-1]")) {//regex permite a expansão da lista no futuro
            throw new IllegalArgumentException("Valor não permitido: " + situacao);
        } else {
            this.situacao = situacao;
        }
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }
    public void setDtCadastro(LocalDate dtCadastro) {
        if (!dtCadastro.equals(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida.");
        }
        this.dtCadastro = dtCadastro;
    }
}
