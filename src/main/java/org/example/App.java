package org.example;

import net.sf.ehcache.CacheManager;
import org.example.cfg.EntityManagerFactoryProvider;
import org.example.model.entity.Sex;
import org.example.model.entity.User;
import org.example.model.entity.User_;
import org.hibernate.Session;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        User user = new User();
        user.setSex(Sex.MALE);
        user.setAge(12);
        user.setName("GAG");
        EntityManagerFactory emf = EntityManagerFactoryProvider
                .getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Session session = em.unwrap(Session.class);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(root.get(User_.id), 12));
        System.out.println(em.createQuery(criteriaQuery).getSingleResult());
    }
}
