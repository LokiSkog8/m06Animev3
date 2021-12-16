package com.example.demo.domain.model.projection;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionFavorite {
    @JsonIgnoreProperties("favoritedby")
    Set<ProjectionAnimeFavorited> getFavorites();
}