package uk.me.eastmans.meta;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EntityMetaData {
    private String name;
    private List<EntityAttribute> attributes;

    private String identifierAttributeName;

    public EntityMetaData( String entityName ) {
        name = entityName;
    }

    public String getName() {
        return name;
    }

    public List<EntityAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<EntityAttribute> attrs) {
        attributes = attrs;
    }

    public String getIdentifierAttributeName() {
        return identifierAttributeName;
    }

    public void setIdentifierAttributeName(String identifierName) {
        identifierAttributeName = identifierName;
        // We need to update the entity attributes to flag the identifier ones
        for( EntityAttribute attr : attributes) {
            if (attr.getName().equals(identifierName))
                attr.setIsIdentifier(true);
        }
        // Sort the list of attributes again now
        Collections.sort(attributes);
    }

}
