package com.example.examendubios.service;

import com.example.examendubios.domain.Nevoie;
import com.example.examendubios.domain.NevoieCuNume;
import com.example.examendubios.domain.OrasEnum;
import com.example.examendubios.domain.Persoana;
import com.example.examendubios.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {
    private Repository<Long, Persoana> repoPersoane;
    private Repository<Long, Nevoie> repoNevoi;

    public Service(Repository<Long, Persoana> repoPersoane, Repository<Long, Nevoie> repoNevoi) {
        this.repoPersoane = repoPersoane;
        this.repoNevoi = repoNevoi;
    }

    public void savePersoana(Persoana persoana) {
        repoPersoane.save(persoana);
    }

    public List<String> getAllUsernames() {
        List<Persoana> persoane = toList(repoPersoane.findAll());
        List<String> usernames = new ArrayList<>();
        for (Persoana persoana : persoane) {
            usernames.add(persoana.getUsername());
        }
        return usernames;

    }

    public Persoana getUser(String username) {
        List<Persoana> persoane = toList(repoPersoane.findAll());
        for (Persoana persoana : persoane) {
            if (Objects.equals(persoana.getUsername(), username))
                return persoana;
        }
        return null;
    }

    public List<NevoieCuNume> getAllNevoiOfOras(Persoana persoana) {
        List<Nevoie> nevoi = new ArrayList<>();
        for (Nevoie _nevoie : toList(repoNevoi.findAll())) {
            if (getOrasulNevoi(_nevoie) == persoana.getOras() && !Objects.equals(_nevoie.getOmInNevoie(), persoana.getId()))
                nevoi.add(_nevoie);
        }

        List<NevoieCuNume> nevoiCuNume=new ArrayList<>();
        for(Nevoie nevoie:nevoi) {
            NevoieCuNume nevoieCuNume=new NevoieCuNume(nevoie.getId(),nevoie.getTitlu(),nevoie.getDescriere(),
                    nevoie.getDeadline(),getNumeOmInNevoie(nevoie),getNumeOmSalvator(nevoie),nevoie.getStatus());
            nevoiCuNume.add(nevoieCuNume);
        }
        return nevoiCuNume;
    }

    public OrasEnum getOrasulNevoi(Nevoie nevoie) {
        for (Persoana persoana : toList(repoPersoane.findAll())) {
            if (Objects.equals(persoana.getId(), nevoie.getOmInNevoie())) {
                return persoana.getOras();
            }
        }
        return null;
    }

    public static <E> List<E> toList(final Iterable<E> iterable) {
        List<E> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public void salveazaOm(Persoana personaSalvatore,Nevoie nevoie){
        Nevoie nevoie1=new Nevoie(nevoie.getId(),nevoie.getTitlu(),
                nevoie.getDescriere(),nevoie.getDeadline(),nevoie.getOmInNevoie()
                ,nevoie.getOmSalvator(),nevoie.getStatus());
        nevoie1.setOmSalvator(personaSalvatore.getId());
        nevoie1.setStatus("Erou gasit!");
        repoNevoi.update(nevoie1);

    }

    public List<NevoieCuNume> getAllNevoiFacuteDePersoana(Persoana persoana){
        List<Nevoie> nevoi=new ArrayList<>();
        for(Nevoie nevoie:toList(repoNevoi.findAll())){
            if(nevoie.getOmSalvator()==persoana.getId())
                nevoi.add(nevoie);
        }
        List<NevoieCuNume> nevoiCuNume=new ArrayList<>();
        for(Nevoie nevoie:nevoi) {
            NevoieCuNume nevoieCuNume=new NevoieCuNume(nevoie.getId(),nevoie.getTitlu(),nevoie.getDescriere(),
                    nevoie.getDeadline(),getNumeOmInNevoie(nevoie),getNumeOmSalvator(nevoie),nevoie.getStatus());
            nevoiCuNume.add(nevoieCuNume);
        }
        return nevoiCuNume;
    }

    public void addNevoie(Nevoie nevoie){
        repoNevoi.save(nevoie);
    }

    public Long getMaxNevoieId(){
        Long maxId=(long)1;
        for(Nevoie nevoie:toList(repoNevoi.findAll())){
            if(nevoie.getId()>maxId)
                maxId=nevoie.getId();
        }
        return maxId+1;
    }

    public Long getMaxPersoanaId(){
        Long maxId=(long)1;
        for(Persoana persoana:toList(repoPersoane.findAll())){
            if(persoana.getId()>maxId)
                maxId=persoana.getId();
        }
        return maxId+1;
    }

    public String getNumeOmInNevoie(Nevoie nevoie){
        for(Persoana persoana:toList(repoPersoane.findAll())){
            if(Objects.equals(nevoie.getOmInNevoie(), persoana.getId()))
                return persoana.getNume();
        }
        return null;
    }

    public String getNumeOmSalvator(Nevoie nevoie){
        for(Persoana persoana:toList(repoPersoane.findAll())){
            if(Objects.equals(nevoie.getOmSalvator(), persoana.getId()))
                return persoana.getNume();
        }
        return null;
    }

    public Nevoie fromNevoieCuNumeToNevoie(NevoieCuNume nevoieCuNume){
        Long omSalvatorId=null;
        Long omInNevoieId=null;
        for(Persoana persoana:toList(repoPersoane.findAll())){
            if(Objects.equals(persoana.getNume(), nevoieCuNume.getOmInNevoie()))
                omInNevoieId=persoana.getId();
            if(Objects.equals(persoana.getNume(), nevoieCuNume.getOmSalvator()))
                omSalvatorId=persoana.getId();
        }
        Nevoie nevoie=new Nevoie(nevoieCuNume.getId(),nevoieCuNume.getTitlu(),nevoieCuNume.getDescriere(),nevoieCuNume.getDeadline(),omInNevoieId,omSalvatorId,nevoieCuNume.getStatus());
        return nevoie;
    }
}
