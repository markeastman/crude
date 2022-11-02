package uk.me.eastmans.meta;

public class EntityAttribute implements Comparable<EntityAttribute> {
    private String name;
    private boolean isAssociation = false;
    private String associatedEntityName = null;

    public EntityAttribute( String attrName ) {
        name = attrName;
    }

    public String getName() {
        return name;
    }

    public boolean isAssociation() {
        return isAssociation;
    }

    public void setAssociation(boolean association) {
        isAssociation = association;
    }

    public String getAssociatedEntityName() {
        return associatedEntityName;
    }

    public void setAssociatedEntityName( String name ) {
        associatedEntityName = name;
    }

    public int compareTo(EntityAttribute b) {
        return name.compareTo(b.getName());
    }
}
