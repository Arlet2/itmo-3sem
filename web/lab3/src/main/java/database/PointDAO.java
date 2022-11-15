package database;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PointDAO implements AbstractPointDAO {
    public void addPoint(Row row) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(row);

        transaction.commit();

        session.close();
    }

    public List<Row> getPoints() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        Query query = session.createQuery("FROM Row", Row.class);

        List<Row> result = query.getResultList();
        session.close();

        return result;
    }

    @Override
    public void removeAllPoints() {
    }
}
