package com.example.examendubios.domain;

import java.io.Serial;
import java.io.Serializable;

public class Entity<ID> implements Serializable {
    @Serial
    private static final long serialVersionUID = 4500794925126064682L;

    protected ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
}
