package fr.utbm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SessionDeFormation implements Serializable {

    private int id;
    private Date debut;
    private Date fin;
    private int capacite;
    private Lieu emplacement;

    public SessionDeFormation() {
    }

    public SessionDeFormation(int id, Date debut, Date fin, int capacite, Lieu emplacement) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.capacite = capacite;
        this.emplacement = emplacement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Lieu getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Lieu emplacement) {
        this.emplacement = emplacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDeFormation that = (SessionDeFormation) o;
        return getId() == that.getId() &&
                getCapacite() == that.getCapacite() &&
                Objects.equals(getDebut(), that.getDebut()) &&
                Objects.equals(getFin(), that.getFin()) &&
                Objects.equals(getEmplacement(), that.getEmplacement());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDebut(), getFin(), getCapacite(), getEmplacement());
    }

    @Override
    public String toString() {
        return "Session De Formation{" +
                "id : " + id +
                ", debut : " + debut +
                ", fin : " + fin +
                ", capacite : " + capacite +
                ", emplacement : " + emplacement +
                '}';
    }
}
