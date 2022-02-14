package com.example.demo.domain.model.projection;

import com.example.demo.domain.model.Author;
import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

public interface ProjectionAnime {
    UUID getAnimeid();
    String getTitle();
    String getSynopsis();
    int getYeare();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
    @JsonIgnoreProperties("animes")
    Set<ProjectionGenre> getGenres();
    @JsonIgnoreProperties("favorites")
    Set<ProjectionUser> getFavoritedby();
}