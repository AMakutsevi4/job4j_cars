package ru.job4j.cars.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Post> getPostLastDay() {
        List<Post> rsl = new ArrayList<>();
        this.tx(session -> {
            LocalDateTime time = LocalDateTime.now().minusDays(1);
            return session.createQuery("select p from Post p "
                    + "join p.driver "
                    + "join p.car "
                    + "join p.photo "
                    + "join p.description "
                    + "join p.sales "
                    + "where p.created >= :time", Post.class).setParameter("time", time).getResultList();
        });
        return rsl;
    }

    public List<Post> getPostPhoto() {
        List<Post> rsl = new ArrayList<>();
        this.tx(session -> {
            return session.createQuery("select p from Post p "
                    + "join p.driver "
                    + "join p.car "
                    + "join p.photo "
                    + "join p.description "
                    + "join p.sales "
                    + "where p.photo is not null ", Post.class).getResultList();
        });
        return rsl;
    }

    public List<Post> getPostBrand() {
        List<Post> rsl = new ArrayList<>();
        this.tx(session -> {
            return session.createQuery("select p from Post p "
                            + "join p.driver "
                            + "join p.car "
                            + "join p.photo "
                            + "join p.description "
                            + "join p.sales "
                            + "where p.brand = :pBrand", Post.class)
                    .setParameter("pBrand", getPostBrand())
                    .getResultList();
        });
        return rsl;
    }
}
