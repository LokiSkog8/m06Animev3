package com.example.demo.repository;

import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.projection.ProjectionGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
    <T> List<T> findBy();

}
