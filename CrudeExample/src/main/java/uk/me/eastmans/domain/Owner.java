package uk.me.eastmans.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table (name= "Owner")
public class Owner {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private Set<Artefact> artefacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
