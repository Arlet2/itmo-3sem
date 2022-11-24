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

    public void addPoint(Point point) {
        Session session = factory.openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.persist(point);

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null)
                transaction.rollback();
            throw new DatabaseException("Cannot add point to database");
        } finally {
            session.close();
        }


    }

    public List<Point> getPoints() {
        Session session = factory.openSession();

        Query query = session.createQuery("FROM Point", Point.class);

        List<Point> result = query.getResultList();
        session.close();

        return result;
    }

    @Override
    public void removeAllPoints() {
    }
}
