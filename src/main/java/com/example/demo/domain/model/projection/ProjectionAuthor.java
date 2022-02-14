package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAuthor {
    UUID getAuthorid();
    String getName();

    @JsonIgnoreProperties("authors")
    Set<ProjectionAnime> getAnimes();
}
