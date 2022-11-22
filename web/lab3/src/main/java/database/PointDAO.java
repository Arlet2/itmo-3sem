package database;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PointDAO implements AbstractPointDAO {
    private final SessionFactory factory;

    public PointDAO() {
        factory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    public void addPoint(Row row) {
        Session session = factory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.persist(row);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            throw new DatabaseException("Cannot add point to database");
        } finally {
            session.close();
        }


    }

    public List<Row> getPoints() {
        Session session = factory.openSession();

        Query query = session.createQuery("FROM Row", Row.class);

        List<Row> result = query.getResultList();
        session.close();

        return result;
    }

    @Override
    public void removeAllPoints() {
    }
}
