package com.example.demo.repository;
import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projection.ProjectionGenre;
import com.example.demo.domain.model.projection.ProjectionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
    List<ProjectionUser> findBy();
    }
