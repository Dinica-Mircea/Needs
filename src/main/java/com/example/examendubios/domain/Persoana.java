package com.example.examendubios.domain;

public class Persoana extends Entity<Long>{
    private String nume;
    private String prenume,username,parola,strada,numarStrada,telefon;
    private OrasEnum oras;
    public Persoana(Long aLong, String nume, String prenume, String username, String parola, OrasEnum oras, String strada, String numarStrada, String telefon) {
        super(aLong);
        this.nume = nume;
        this.prenume = prenume;
        this.username = username;
        this.parola = parola;
        this.oras = oras;
        this.strada = strada;
        this.numarStrada = numarStrada;
        this.telefon = telefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public OrasEnum getOras() {
        return oras;
    }

    public void setOras(OrasEnum oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumarStrada() {
        return numarStrada;
    }

    public void setNumarStrada(String numarStrada) {
        this.numarStrada = numarStrada;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", strada='" + strada + '\'' +
                ", numarStrada='" + numarStrada + '\'' +
                ", telefon='" + telefon + '\'' +
                ", oras=" + oras +
                ", id=" + id +
                '}';
    }
}
