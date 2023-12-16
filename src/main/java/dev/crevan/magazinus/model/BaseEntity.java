package dev.crevan.magazinus.model;

import org.springframework.data.annotation.Id;

public abstract class BaseEntity {

    @Id
    private String id;
}
