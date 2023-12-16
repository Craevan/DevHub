package dev.crevan.magazinus.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity {

    private String name;
    private String description;
    private int price;
}


