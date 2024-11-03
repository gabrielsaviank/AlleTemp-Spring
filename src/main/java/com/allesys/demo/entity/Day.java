package com.allesys.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "day")
public class Day {
    @Id
    private String id;
    @DBRef
    private List<Temperature> temperatures;
    private Date created;

    public Day() {
        this.created = new Date();
        this.temperatures = new ArrayList<>();
    }

    public Day(Date created, List<Temperature> temperatures) {
        this.created = created;
        this.temperatures = temperatures != null ? temperatures : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Date getCreated() {
        return created;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
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
