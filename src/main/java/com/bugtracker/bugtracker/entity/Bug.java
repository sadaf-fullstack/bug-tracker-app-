package com.bugtracker.bugtracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime createdAt;

    // AUTO TIMESTAMP
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters
    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public Status getStatus() { return status; }

    public Priority getPriority() { return priority; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser() { return user; }

    // setters
    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public void setStatus(Status status) { this.status = status; }

    public void setPriority(Priority priority) { this.priority = priority; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public void setUser(User user) { this.user = user; }
}