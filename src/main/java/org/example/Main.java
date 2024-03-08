package org.example;

import org.example.dao.ProjectDao;
import org.example.dao.TaskDao;
import org.example.dao.UserDao;
import org.example.entities.Project;
import org.example.entities.Task;
import org.example.entities.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // Créeation des nouveaux utilisateurs
        UserDao userDao = new UserDao();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setName("omar El Fadili");
        user1.setEmail("omar@gmail.com");
        user2.setName("amine El Fadili");
        user2.setEmail("amine@gmail.com");
        user3.setName("ali El Fadili");
        user3.setEmail("ali@gmail.com");
//
//        // Persiste l'utilisateur
        userDao.persistUser(user1);
        userDao.persistUser(user2);
        userDao.persistUser(user3);

        // Trouver tous les utilisateurs triés par ID
        List<User> users = userDao.findUsersTriAscById();
        System.out.println("Liste des utilisateurs triés par ID: ***********************************");
        for (User u : users) {
            System.out.println(u);
        }
//
//        // Suppression un utilisateur par ID
        Long userIdToDelete = 1L; // L'ID de l'utilisateur à supprimer
        userDao.deleteUserById(userIdToDelete);
//
        // Création des nouveaux projets
        //prjet 1
        ProjectDao projectDao = new ProjectDao();
        Project project1 = new Project();
        project1.setTitle("Project A");
        project1.setDescription("Description du projet A");
        project1.setUser(user1); // Associer le projet à l'utilisateur créé
        //projet 2
        Project project2 = new Project();
        project2.setTitle("Project A");
        project2.setDescription("Description du projet A");
        project2.setUser(user2); // Associer le projet à l'utilisateur créé
        // Persiste des projets
        projectDao.persistProject(project1);
        projectDao.persistProject(project2);

        // Trouver tous les projets triés par ID
        List<Project> projects = projectDao.findProjectsTriAscById();
        System.out.println("Liste des projets triés par ID: ********************************");
        for (Project p : projects) {
            System.out.println(p);
        }

        // Créer une nouvelle tâche et l'ajouter à un projet
        TaskDao taskDao = new TaskDao();
        Task task1 = new Task();
        task1.setTitle("Task A");
        task1.setProject(project1); // Associer la tâche au projet créé
        Task task2 = new Task();
        task2.setTitle("Task A");
        task2.setProject(project1); // Associer la tâche au projet créé

        // Ajouter la tâche au projet
        taskDao.addTaskTOProject(project2, task2);

        // Supprimer toutes les tâches liées à un projet
        Long projectIdToDeleteTasks = 1L; // L'ID du projet dont les tâches doivent être supprimées
        taskDao.deleteTasksByProjectId(projectIdToDeleteTasks);
    }
}