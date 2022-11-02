package uk.me.eastmans.meta;

import java.util.Set;

public class EntityMetaData {
    private String name;
    private Set<EntityAttribute> attributes;

    private String identifierAttributeName;

    public EntityMetaData( String entityName ) {
        name = entityName;
    }

    public String getName() {
        return name;
    }

    public Set<EntityAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<EntityAttribute> attrs) {
        attributes = attrs;
    }

    public String getIdentifierAttributeName() {
        return identifierAttributeName;
    }

    public void setIdentifierAttributeName(String identfierName) {
        identifierAttributeName = identfierName;
    }

}
