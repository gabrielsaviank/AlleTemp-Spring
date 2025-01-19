package com.allesys.demo.controller;

import com.allesys.demo.entity.Temperature;
import com.allesys.demo.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping
    public List<Temperature> getAllTemperatures() {
        return temperatureService.getAllTemperatures();
    }

    @GetMapping("/{id}")
    public Optional<Temperature> getTemperatureById(@PathVariable String id) {
        return temperatureService.getTemperatureById(id);
    }

    @GetMapping("/lastDayTemps")
    public List<Double> getLastDayTemperatures() {
        return temperatureService.getLastDayTemperatures();
    }

    // Create delete, update
    @PostMapping
    public ResponseEntity<Temperature> createMeasure(@RequestParam Double measure) {
        Temperature temperature = temperatureService.createMeasure(measure);
        return ResponseEntity.status(HttpStatus.CREATED).body(temperature);
    }

    @PutMapping("/{id}")
    public Temperature updateTemperature(@PathVariable String id, @RequestBody Temperature temperature) {
        temperature.setId(id);
        return temperatureService.updateTemperature(id, temperature);
    }

    @DeleteMapping("/{id}")
    public void deleteTemperature(@PathVariable String id) {
        temperatureService.deleteTemperature(id);
    }
}
