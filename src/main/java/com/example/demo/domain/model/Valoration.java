package com.example.demo.domain.model;


import com.example.demo.domain.model.compositekeys.ValorationUserAnimeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="valoration")
@IdClass(ValorationUserAnimeKey.class)
public class Valoration {
    @Id
    public UUID animeid;
    @Id
    public UUID userid;
    public double valoration;
}
