package com.example.demo.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionGenre {
    UUID getGenre();
    String getLabel();
    @JsonIgnoreProperties("genres")
    Set<ProjectionAnime> getAnimes();
}

