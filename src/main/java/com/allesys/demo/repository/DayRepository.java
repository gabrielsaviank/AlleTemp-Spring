package com.allesys.demo.repository;

import com.allesys.demo.entity.Day;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends MongoRepository<Day, String> {

}
