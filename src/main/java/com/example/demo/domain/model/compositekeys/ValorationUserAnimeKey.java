package com.example.demo.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class ValorationUserAnimeKey implements Serializable {
    public UUID animeid;
    public UUID userid;
    public int valoration;
}
