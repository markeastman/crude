package uk.me.eastmans.meta;

import java.util.Set;

public class EntityMetaData {
    private String name;
    private Set<String> attributeNames;

    private String identifierAttributeName;

    public EntityMetaData( String entityName ) {
        name = entityName;
    }

    public String getName() {
        return name;
    }

    public Set<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(Set<String> names) {
        attributeNames = names;
    }

    public String getIdentifierAttributeName() {
        return identifierAttributeName;
    }

    public void setIdentifierAttributeName(String identfierName) {
        identifierAttributeName = identfierName;
    }

}
