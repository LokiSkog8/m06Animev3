package com.example.demo.repository;

import com.example.demo.domain.model.Author;
import com.example.demo.domain.model.projection.ProjectionAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<ProjectionAuthor> findBy();
}