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
            data = new EntityMetaData(entityName);
            Metamodel metaModel = entityManager.getMetamodel();
            Set<ManagedType<?>> managedTypes = metaModel.getManagedTypes();
            // We need to find the type requested so we can get the fields etc.
            for (ManagedType<?> type : managedTypes) {
                if (type instanceof EntityType<?> entityType) {
                    if (entityType.getName().equals(entityName)) {
                        processEntity(data,entityType);
                        break;
                    }
                }
            }
            store.put(entityName, data);
        }

        return data;
    }

    private <T> void processEntity( EntityMetaData metaData, EntityType<T> entityType)
    {
        final Set<Attribute<? super T, ?>> attrs = entityType.getAttributes();
        // Build a set of attribute names
        Set<String> properties = new TreeSet<String>();
        for (Attribute<? super T, ?> attribute : attrs) {
            properties.add(attribute.getName());
        }
        metaData.setAttributeNames(properties);
        // Now find the identifier one
        final Set<SingularAttribute<? super T, ?>> singleAttrs = entityType.getSingularAttributes();
        for (SingularAttribute<? super T, ?> attribute : singleAttrs) {
            if (attribute.isId())
                metaData.setIdentifierAttributeName( attribute.getName() );
        }
//        String name = entityType.getId(entityType.getIdType().getJavaType()).getName();
    }
}
