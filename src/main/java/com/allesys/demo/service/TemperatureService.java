package com.allesys.demo.service;

import com.allesys.demo.entity.Temperature;
import com.allesys.demo.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureService {
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public List<Temperature> getAllTemperatures(){
        return temperatureRepository.findAll();
    }

    public Optional<Temperature> getTemperatureById(String temperatureId){
        return temperatureRepository.findById(temperatureId);
    }

    public Temperature createTemperature(Temperature temperature){
        return temperatureRepository.save(temperature);
    }

    public void deleteTemperature(String temperatureId){
        temperatureRepository.deleteById(temperatureId);
    }

    public Temperature updateTemperature(String temperatureId, Temperature temperature){
        return temperatureRepository.save(temperature);
    }
}
