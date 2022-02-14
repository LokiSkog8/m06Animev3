package com.example.demo.repository;

import com.example.demo.domain.model.Anime;
import com.example.demo.domain.model.projection.ProjectionAnime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
    <T> List<T> findBy(Class<T> clazz);
    <T> List<T> findByAnimeid(UUID id, Class<T> type);

}