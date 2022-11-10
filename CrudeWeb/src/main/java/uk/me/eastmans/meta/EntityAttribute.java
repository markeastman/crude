package uk.me.eastmans.meta;

public class EntityAttribute implements Comparable<EntityAttribute> {
    private String name;
    private boolean isAssociation = false;

    private boolean isIdentifier = false;

    private boolean isCollection = false;

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

    public boolean isIdentifier() {
        return isIdentifier;
    }

    public void setIsIdentifier(boolean state) {
        isIdentifier = state;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean state) { isCollection = state; }
    public int compareTo(EntityAttribute b) {
        // Check for identifier and bring to the front
        if (isIdentifier)
            return -1;
        if (b.isIdentifier())
            return 1;
        return name.compareTo(b.getName());
    }
}
