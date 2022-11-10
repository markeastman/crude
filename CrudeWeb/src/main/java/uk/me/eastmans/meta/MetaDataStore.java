package uk.me.eastmans.meta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.*;

import java.util.*;

public class MetaDataStore {

    private Map<String,EntityMetaData> store = new HashMap<>();
    private EntityManager entityManager;

    public MetaDataStore(EntityManager em) {
        entityManager = em;
    }

    public EntityMetaData getEntityMetaData( String entityName ) {
        EntityMetaData data = store.get(entityName);
        if (data == null) {
            // Build the data object and store back in the map
            Metamodel metaModel = entityManager.getMetamodel();
            Set<ManagedType<?>> managedTypes = metaModel.getManagedTypes();
            // We need to find the type requested so we can get the fields etc.
            for (ManagedType<?> type : managedTypes) {
                if (type instanceof EntityType<?> entityType) {
                    if (entityType.getName().equals(entityName)) {
                        data = new EntityMetaData(entityName);
                        processEntity(data,entityType);
                        break;
                    }
                }
            }
            store.put(entityName, data);
        }

        return data;
    }

    private <T> void processEntity( EntityMetaData metaData, EntityType<T> entityType) {
        final Set<Attribute<? super T, ?>> attrs = entityType.getAttributes();
        // Build a set of attribute names
        List<EntityAttribute> attributes = new ArrayList<EntityAttribute>();
        for (Attribute<? super T, ?> attribute : attrs) {
            EntityAttribute entityAttribute = new EntityAttribute(attribute.getName());
            // Check to see if the attribute is a collection, if so
            // for now take off the association aspect as we will mess up the display
            attributes.add(entityAttribute);
            entityAttribute.setIsCollection(attribute.isCollection());
            if (!attribute.isCollection()) {
                entityAttribute.setAssociation(attribute.isAssociation());
                if (attribute instanceof SingularAttribute) {
                    SingularAttribute singleAttr = (SingularAttribute) attribute;
                    if (singleAttr.isId()) {
                        entityAttribute.setIsIdentifier(true);
                        metaData.setIdentifierAttributeName(singleAttr.getName());
                    }
                    if (singleAttr.isAssociation()) {
                        // We need to know what type of foreign object is it and
                        // also what is the identifier value of that linked object
                        EntityType associatedEntityType = (EntityType) singleAttr.getType();
                        entityAttribute.setAssociatedEntityName(associatedEntityType.getName());
                    }
                }
            }
        }
        // Sort the list of attributes again now
        Collections.sort(attributes);
        metaData.setAttributes(attributes);
    }
 }
