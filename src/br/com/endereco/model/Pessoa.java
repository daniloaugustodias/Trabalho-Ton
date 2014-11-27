package br.com.endereco.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model da classe Pessoa.
 *
 * @author FHFG
 */
public class Pessoa {

    private final StringProperty nome;
    private final StringProperty sobrenome;
    private final StringProperty rua;
    private final IntegerProperty cep;
    private final StringProperty cidade;
    private final ObjectProperty<LocalDate> dataAniversario;

    public Pessoa() {
        this(null, null, null, 0, null, null);
    }

    public Pessoa(String nome, String sobrenome, String rua, Integer cep, String cidade, Object dtAniversario) {
        this.nome            = new SimpleStringProperty(nome);
        this.sobrenome       = new SimpleStringProperty(sobrenome);
        this.rua             = new SimpleStringProperty(rua);
        this.cep             = new SimpleIntegerProperty(cep);
        this.cidade          = new SimpleStringProperty(cidade);
        this.dataAniversario = new SimpleObjectProperty<LocalDate>((LocalDate) dtAniversario);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome.get();
    }

    public void setSobrenome(String sbName) {
        this.sobrenome.set(sbName);
    }

    public StringProperty sobrenomeProperty() {
        return sobrenome;
    }

    public String getRua() {
        return rua.get();
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public int getCEP() {
        return cep.get();
    }

    public void setCEP(int numCep) {
        this.cep.set(numCep);
    }

    public IntegerProperty cepProperty() {
        return cep;
    }

    public String getCidade() {
        return cidade.get();
    }

    public void setCidade(String city) {
        this.cidade.set(city);
    }

    public StringProperty cidadeProperty() {
        return cidade;
    }

    public LocalDate getAniversario() {
        return dataAniversario.get();
    }

    public void setAniversario(LocalDate birthday) {
        this.dataAniversario.set(birthday);
    }

    public ObjectProperty<LocalDate> aniversarioProperty() {
        return dataAniversario;
    }
}