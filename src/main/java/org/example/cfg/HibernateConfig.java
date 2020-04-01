package org.example.cfg;

import com.mysql.cj.jdbc.Driver;
import org.example.model.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.BasicType;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ServiceRegistry serviceRegistry = null;
            try {
                serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(getHibernateSettings())
                                .build();
                Metadata metadata =
                        new MetadataSources(serviceRegistry)
                                .addAnnotatedClass(User.class)
                                .getMetadataBuilder()
//                                .applyPhysicalNamingStrategy(new DemoNamingStrategy())
                                .build();
                SessionFactoryBuilder sessionFactoryBuilder =
                        metadata.getSessionFactoryBuilder();
                sessionFactory = sessionFactoryBuilder
                        .applyInterceptor(new LogInterceptor())
                        .build();
            } catch (Exception e) {
                System.out.println("Closing opened resources...");
                if (serviceRegistry != null)
                    serviceRegistry.close();
                if (sessionFactory != null)
                    sessionFactory.close();
                e.printStackTrace();
            }

        }
        return sessionFactory;
    }

    private static Properties getHibernateSettings() {
        Properties properties = new Properties();
        properties.setProperty(HBM2DDL_AUTO, "update");
        properties.setProperty(URL, "jdbc:mysql://localhost:3306/hibernate_training?useSSL=false");
        properties.setProperty(USER, "root");
        properties.setProperty(PASS, "root");
        properties.setProperty(DRIVER, Driver.class.getCanonicalName());
        properties.setProperty(DIALECT, MySQL57Dialect.class.getCanonicalName());
        properties.setProperty(SHOW_SQL, Boolean.toString(true));
        return properties;
    }
}
