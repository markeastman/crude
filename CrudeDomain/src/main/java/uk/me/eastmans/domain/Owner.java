package uk.me.eastmans.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "Owner")
public class Owner {
    @Id
    @GeneratedValue
    private int id;

    @Basic
    private String name;

    @OneToMany
    private Set<Artefact> artefacts = new HashSet<Artefact>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Artefact> getArtefacts() {
        return artefacts;
    }

    public void setArtefacts(Set<Artefact> artefacts) {
        this.artefacts = artefacts;
    }

    public void addArtefact(Artefact artefact) {
        this.artefacts.add(artefact);
    }

    public void removeArtefact(Artefact artefact) { this.artefacts.remove(artefact); }
    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artefacts=" + artefacts +
                '}';
    }
}
