package com.allesys.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "temperature")
public class Temperature {
    @Id
    private String id;

    @DBRef
    private Day day;
    private Double measure;
    private Date time;

    public Temperature() {
        this.time = new Date();
    }

    public Temperature(Day day, Double measure) {
        this.day = day;
        this.measure = measure;
        this.time = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Double getMeasure() {
        return measure;
    }

    public void setMeasure(Double measure) {
        this.measure = measure;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
