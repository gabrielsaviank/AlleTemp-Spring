package com.allesys.demo.repository;

import com.allesys.demo.entity.Day;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DayRepository extends MongoRepository<Day, String> {
    Optional<Day> findByCreated(Date created);
}
