package dev.crevan.magazinus.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public abstract class BaseEntity {

    @Id
    private String id;
}
