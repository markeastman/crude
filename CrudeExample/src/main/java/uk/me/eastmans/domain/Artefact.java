package uk.me.eastmans.domain;

import jakarta.persistence.*;

@Entity
public class Artefact {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Owner owner;


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
        this.owner = owner;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }
}
