package fr.utbm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name="formation")
public class Formation implements Serializable {

    @Id
    @Column(name="code", columnDefinition="char")
    private String code;

    @Column(name="intitule")
    private String intitule;

    @OneToMany (fetch=FetchType.EAGER)
    @JoinColumn(name="formation_code")
    private List<SessionDeFormation> listeSessions;

    public Formation() {
    }

    public Formation(String code, String title) {
        this.code = code;
        this.intitule = title;
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

    public List<SessionDeFormation> getListeSessions() {
        return listeSessions;
    }

    public void addSession(SessionDeFormation session) {
        listeSessions.add(session);
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
