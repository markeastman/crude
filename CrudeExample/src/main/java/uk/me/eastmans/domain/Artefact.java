package uk.me.eastmans.domain;

import jakarta.persistence.*;

@Entity
public class Artefact {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Owner owner;

    @Basic
    private String name;

    @OneToOne
    private LifeCycle lifeCycle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        // remove the artefact from the existing owner
        if (this.owner != null)
            this.owner.removeArtefact(this);
        this.owner = owner;
        // inform the owner of this
        if (owner != null)
            owner.addArtefact(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return "Artefact{" +
                "id=" + id +
                ", owner=" + owner.getName() +
                ", name='" + name + '\'' +
                ", lifeCycle=" + lifeCycle +
                '}';
    }
}
