package fr.utbm.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Formation implements Serializable {

    private String code;
    private String intitule;
    private ArrayList<SessionDeFormation> listeSessions;

    public Formation() {
    }

    public Formation(String code, String title) {
        this.code = code;
        this.intitule = title;
        listeSessions = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String title) {
        this.intitule = title;
    }
    
    public void addSession(SessionDeFormation session) {
        listeSessions.add(session);
    }

    public ArrayList<SessionDeFormation> getListeSessions() {
        return listeSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formation formation = (Formation) o;
        return Objects.equals(getCode(), formation.getCode()) &&
                Objects.equals(getIntitule(), formation.getIntitule());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getIntitule());
    }

    @Override
    public String toString() {
        return "Formation{" +
                "code : " + code + ", intitule : " + intitule + '}';
    }
}
