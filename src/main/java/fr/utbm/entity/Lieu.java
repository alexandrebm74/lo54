package fr.utbm.entity;

import java.io.Serializable;
import java.util.Objects;

public class Lieu implements Serializable {

    private int id;
    private String ville;

    public Lieu() {
    }

    public Lieu(int id, String ville) {
        this.id = id;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lieu lieu = (Lieu) o;
        return getId() == lieu.getId() &&
                Objects.equals(getVille(), lieu.getVille());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getVille());
    }

    @Override
    public String toString() {
        return "Lieu{" +"id : " + id + ", ville : " + ville + '}';
    }
}
