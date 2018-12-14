package fr.utbm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name="utilisateur")
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String numeroTelephone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionDeFormation session;

    public Utilisateur() {
    }

    public Utilisateur(String nom,
                       String prenom,
                       String adresse,
                       String numeroTelephone,
                       String email,
                       SessionDeFormation session) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SessionDeFormation getSession() {
        return session;
    }

    public void setSession(SessionDeFormation session) {
        this.session = session;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return getId() == that.getId() &&
                Objects.equals(getNom(), that.getNom()) &&
                Objects.equals(getPrenom(), that.getPrenom()) &&
                Objects.equals(getAdresse(), that.getAdresse()) &&
                Objects.equals(getNumeroTelephone(), that.getNumeroTelephone()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getSession(), that.getSession());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getNom(), getPrenom(), getAdresse(), getNumeroTelephone(), getEmail(), getSession());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", email='" + email + '\'' +
                ", session=" + session +
                '}';
    }
}
