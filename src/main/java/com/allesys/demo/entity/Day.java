package com.allesys.demo.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "day")
public class Day {
    @Getter
    @Id
    private String id;
    private Date created;

    @Getter
    @DBRef
    private List<Temperature> temperatures = new ArrayList<>();

    public Day() {
        this.created = new Date();
    }

    public Day(Date created, List<Temperature> temperatures) {
        this.created = created;
        this.temperatures = temperatures;
    }

    public Date getCreated() {
        return created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }
}
