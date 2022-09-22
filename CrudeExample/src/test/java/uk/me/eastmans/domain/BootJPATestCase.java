package uk.me.eastmans.domain;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.metamodel.EntityType;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BootJPATestCase {
    @Test
    public void test1() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Crude");
        final Set<EntityType<?>> entities = emf.getMetamodel().getEntities();
        assertTrue(entities.size() > 0, "No entities were found");
        for (EntityType<?> e : entities) {
            System.out.println(e.getName());
        }
    }
}
