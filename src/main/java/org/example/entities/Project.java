package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Project", schema = "your_schema")
@NamedQuery(name = "Project.persist", query = "INSERT INTO Project (title, description, user) VALUES (:title, :description, :user)")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Project(String title, String description, List<Task> tasks, User user) {
        this.title = title;
        this.description = description;
        this.tasks = tasks;
        this.user = user;
    }

    public Project() {

    }
}
