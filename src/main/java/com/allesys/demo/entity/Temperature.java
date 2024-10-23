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

    private String measure;

    private Date time;

    public Temperature() {
        this.time = new Date();
    }

    public Temperature(Double value, Day day, String measure) {
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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
