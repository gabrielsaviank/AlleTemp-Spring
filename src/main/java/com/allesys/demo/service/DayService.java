package com.allesys.demo.service;

import com.allesys.demo.entity.Day;
import com.allesys.demo.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {
    private final DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> getAllDays(){
        return dayRepository.findAll();
    }

    public Optional<Day> getDayById(String id) {
        return dayRepository.findById(id);
    }

    public Optional<Day> getCurrentDayMeasures() {
        return dayRepository.findFirstByOrderByCreatedDesc();
    }

    public Optional<Day> getDayByDate(Date day) {
        return dayRepository.findByCreated(day);
    }

    public Day createDay(Day day) {
        return dayRepository.save(day);
    }

    public void deleteDay(String id) {
        dayRepository.deleteById(id);
    }

    public Day updateDay(Day day) {
        return dayRepository.save(day);
    }
}
