package uk.me.eastmans.domain;

import jakarta.persistence.*;

@Entity
public class Consumption {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Artefact consumes;
    @ManyToOne
    private Artefact consumedBy;

    @Basic
    private String documentLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artefact getConsumes() {
        return consumes;
    }

    public void setConsumes(Artefact consumes) {
        this.consumes = consumes;
    }

    public Artefact getConsumedBy() {
        return consumedBy;
    }

    public void setConsumedBy(Artefact consumedBy) {
        this.consumedBy = consumedBy;
    }

    public String getDocumentLocation() {
        return documentLocation;
    }

    public void setDocumentLocation(String documentLocation) {
        this.documentLocation = documentLocation;
    }


    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", consumes=" + consumes +
                ", consumedBy=" + consumedBy +
                ", documentLocation='" + documentLocation + '\'' +
                '}';
    }
}
