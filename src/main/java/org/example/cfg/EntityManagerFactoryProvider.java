package org.example.cfg;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("CRM");
            }
        } catch (Exception e) {
            if (emf != null)
                emf.close();
            e.printStackTrace();
        }

        return emf;
    }
}
