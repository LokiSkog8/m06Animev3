package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAuthor {
    UUID getAuthor();
    String getTitle();
    @JsonIgnoreProperties("authors")
    Set<ProjectionAnime> getAnimes();
}
