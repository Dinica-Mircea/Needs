package com.example.examendubios.domain;

import java.time.LocalDateTime;

public class NevoieCuNume extends Entity<Long>{
    private String titlu,descriere;
    private LocalDateTime deadline;
    private String omInNevoie,omSalvator;
    private String status;

    public NevoieCuNume(Long aLong, String titlu, String descriere, LocalDateTime deadline, String omInNevoie, String omSalvator, String status) {
        super(aLong);
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.omInNevoie = omInNevoie;
        this.omSalvator = omSalvator;
        this.status = status;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getOmInNevoie() {
        return omInNevoie;
    }

    public void setOmInNevoie(String omInNevoie) {
        this.omInNevoie = omInNevoie;
    }

    public String getOmSalvator() {
        return omSalvator;
    }

    public void setOmSalvator(String omSalvator) {
        this.omSalvator = omSalvator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
