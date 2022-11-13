package org.example.springmvc.repository.message;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@AllArgsConstructor
@Setter
public class MessageRepository implements MessageDao {
    private SessionFactory sessionFactory;

    @Override
    public void saveMessage(final Message message) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getUserMessages(final User user1, final User user2) {
        List<Message> userDialog = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getUserDialog")
                    .setParameter("user1", user1)
                    .setParameter("user2", user2);
            userDialog = (List<Message>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDialog;
    }

    @Override
    public void removeMessages(final List<Message> dialog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete Message m where m in (:dialog)")
                    .setParameter("dialog", dialog)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
        }
    }
}
