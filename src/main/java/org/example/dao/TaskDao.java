package org.example.dao;

import org.example.entities.Project;
import org.example.entities.Task;
import org.example.exceptions.TaskNotFoundException;
import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TaskDao {
    public List<Task> findTasksTriAscById() {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        return entityManager.createQuery("SELECT t FROM Task t ORDER BY t.id ASC", Task.class)
                .getResultList();
    }
    public void addTaskTOProject(Project project, Task task) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        task.setProject(project); // Associer la tâche au projet existant
        entityManager.persist(task); // Persister la tâche
        PersistenceManager.closeEntityManagerFactory();
    }
    public void deleteTasksByProjectId(Long projectId) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            int deletedCount = entityManager.createQuery("DELETE FROM Task t WHERE t.project.id = :projectId")
                    .setParameter("projectId", projectId)
                    .executeUpdate();

            if (deletedCount == 0) {
                throw new TaskNotFoundException("Aucune tâche n'a été trouvée pour le projet avec l'identifiant " + projectId);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            PersistenceManager.closeEntityManagerFactory();
        }
    }
}
