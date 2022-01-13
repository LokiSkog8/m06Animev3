package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.MovieUserKey;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name="favorite")
@IdClass(MovieUserKey.class)
public class Favorite {
    @Id
    public UUID userid;
    @Id
    public UUID animeid;
}
