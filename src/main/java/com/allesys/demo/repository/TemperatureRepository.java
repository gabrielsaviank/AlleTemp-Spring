package com.allesys.demo.repository;

import com.allesys.demo.entity.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, String> {
    List<Temperature> findByTimeAfterOrderByTimeDesc(Date time);
}
