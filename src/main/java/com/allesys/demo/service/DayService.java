package com.allesys.demo.service;

import com.allesys.demo.entity.Day;
import com.allesys.demo.entity.Temperature;
import com.allesys.demo.repository.DayRepository;
import com.allesys.demo.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DayService {
    private final DayRepository dayRepository;
    private final TemperatureService temperatureService;

    @Autowired
    public DayService(DayRepository dayRepository, TemperatureService temperatureService) {
        this.dayRepository = dayRepository;
        this.temperatureService = temperatureService;
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
        Optional<Day> dayToFind = dayRepository.findByCreated(day);

        if (dayToFind.isPresent()) {
            Day selectedDay = dayToFind.get();

            List<Temperature> temperatures = temperatureService.getTemperaturesByDayId(selectedDay.getId());

            selectedDay.setTemperatures(temperatures);
        }

        return dayToFind;
    }

    public Day createDay(Day day) {
        return dayRepository.save(day);
    }

    public Day updateDay(Day day) {
        return dayRepository.save(day);
    }

    public void deleteDay(String id) {
        dayRepository.deleteById(id);
    }
}
