package com.allesys.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Document(collection = "day")
public class Day {
    @Id
    private String id;
    private Date created;

    @DBRef
    private List<Temperature> temperatures;

    public Day() {
        this.created = new Date();
    }

    public Day(Date created, List<Temperature> temperatures) {
        this.created = created;
        this.temperatures = temperatures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }
}
