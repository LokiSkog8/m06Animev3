package com.example.demo.domain.model;


import com.example.demo.domain.model.compositekeys.CustomList;
import com.example.demo.domain.model.compositekeys.ValorationUserAnimeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="custom")
@IdClass(CustomList.class)
public class Custom {
    @Id
    public UUID animeid;
    @Id
    public UUID userid;
    public String listname;
}
