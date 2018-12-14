package fr.utbm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="session")
public class SessionDeFormation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="debut")
    private Date debut;

    @Column(name="fin")
    private Date fin;

    @Column(name="capacite")
    private int capacite;

    @ManyToOne
    @JoinColumn(name="formation_code")
    private Formation cours;

    @ManyToOne
    @JoinColumn(name="lieu_id")
    private Lieu emplacement;

    public SessionDeFormation() {
    }

    public SessionDeFormation(int id, Date debut, Date fin, int capacite, Formation cours, Lieu emplacement) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.capacite = capacite;
        this.cours = cours;
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

    public Formation getCours() {
        return cours;
    }

    public void setCours(Formation cours) {
        this.cours = cours;
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
                Objects.equals(getCours(), that.getCours()) &&
                Objects.equals(getEmplacement(), that.getEmplacement());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getDebut(), getFin(), getCapacite(), getCours(), getEmplacement());
    }

    @Override
    public String toString() {
        return "Session De Formation{" +
                "id : " + id +
                ", debut : " + debut +
                ", fin : " + fin +
                ", capacite : " + capacite +
                ", cours : " + cours +
                ", emplacement : " + emplacement +
                '}';
    }
}
