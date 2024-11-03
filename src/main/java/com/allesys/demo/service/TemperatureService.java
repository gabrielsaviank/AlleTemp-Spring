package com.allesys.demo.service;

import com.allesys.demo.entity.Day;
import com.allesys.demo.entity.Temperature;
import com.allesys.demo.repository.DayRepository;
import com.allesys.demo.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.allesys.demo.utils.Utils.DateUtils.isSameDay;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;
    private final DayRepository dayRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository, DayRepository dayRepository) {
        this.temperatureRepository = temperatureRepository;
        this.dayRepository = dayRepository;
    }

    @Transactional
    public Temperature createMeasure(Double measure) {
        Date currentDate = new Date();
        Day currentDay = dayRepository.findFirstByOrderByCreatedDesc().orElse(null);

        if (currentDay == null || !isSameDay(currentDay.getCreated(), currentDate)) {
            currentDay = new Day();
            dayRepository.save(currentDay);
        }

        Temperature temperature = new Temperature();

        temperature.setMeasure(measure);
        temperature.setDay(currentDay);
        temperatureRepository.save(temperature);

        currentDay.getTemperatures().add(temperature);
        dayRepository.save(currentDay);

        return temperature;
    }

    public List<Temperature> getAllTemperatures(){
        return temperatureRepository.findAll();
    }

    public Optional<Temperature> getTemperatureById(String temperatureId){
        return temperatureRepository.findById(temperatureId);
    }

    public void deleteTemperature(String temperatureId){
        temperatureRepository.deleteById(temperatureId);
    }

    public Temperature updateTemperature(String temperatureId, Temperature temperature){
        return temperatureRepository.save(temperature);
    }
}
