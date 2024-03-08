package org.example.dao;

import org.example.entities.Project;
import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectDao {

    public List<Project> findProjectsTriAscById() {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        return entityManager.createQuery("SELECT p FROM Project p ORDER BY p.id ASC", Project.class)
                .getResultList();
    }
    public void persistProject(Project project) {
        EntityManager entityManager = PersistenceManager.getEntityManager();
        entityManager.createNamedQuery("Project.persist")
                .setParameter("title", project.getTitle())
                .setParameter("description", project.getDescription())
                .setParameter("user", project.getUser())
                .executeUpdate();

    }
}
