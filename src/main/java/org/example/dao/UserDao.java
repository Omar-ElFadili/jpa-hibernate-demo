package org.example.dao;

import org.example.entities.User;
import org.example.exceptions.UserNotFoundException;
import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDao {
    public void persistUser(User user) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction=entityManager.getTransaction();

            transaction.begin();

            entityManager.persist(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
    public List<User> findUsersTriAscById() {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        return entityManager.createQuery("SELECT u FROM User u ORDER BY u.id ASC", User.class)
                .getResultList();
    }

    public void deleteUserById(Long userId) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            User user = entityManager.find(User.class, userId);
            if (user == null) {
                throw new UserNotFoundException("L'utilisateur avec l'identifiant " + userId + " n'a pas été trouvé.");
            }

            entityManager.remove(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
