package com.example.TaskManager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTask extends JpaRepository<EntityTask,Long> {
}
