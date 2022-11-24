package org.example.springmvc.repository.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MessageRepository implements MessageDao {
    private final SessionFactory sessionFactory;

    @Override
    public void saveMessage(final Message message) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to save user message. User [{}]", message.getSender() + "\n" + e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getUserMessages(final User firstUser, final User secondUser) {
        List<Message> userDialog = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getUserDialog")
                    .setParameter("user1", firstUser)
                    .setParameter("user2", secondUser);
            userDialog = (List<Message>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to get users messages. Users [{}, {}]",
                    firstUser.getUsername(), secondUser.getUsername() + "\n" + e);
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
            log.error("An error occurred when trying to remove user dialog. Users [{}, {}]",
                    dialog.get(0).getSender(), dialog.get(0).getRecipient() + "\n" + e);
        }
    }
}
