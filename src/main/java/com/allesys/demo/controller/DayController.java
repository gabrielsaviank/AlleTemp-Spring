package com.allesys.demo.controller;

import com.allesys.demo.entity.Day;
import com.allesys.demo.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/days")
public class DayController {
    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping
    public List<Day> getAllDays() {
        return dayService.getAllDays();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable String id) {
        Optional<Day> day = dayService.getDayById(id);

        if (day.isPresent()) {
            return ResponseEntity.ok(day.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/current")
    public ResponseEntity<Day> getCurrentDay() {
        Optional<Day> currentDay = dayService.getCurrentDayMeasures();

        if (currentDay.isPresent()) {
            return ResponseEntity.ok(currentDay.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/by-date")
    public ResponseEntity<Day> getDayByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Optional<Day> day = dayService.getDayByDate(date);

        if (day.isPresent()) {
            return ResponseEntity.ok(day.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public Day createDay(@RequestBody Day day) {
        return dayService.createDay(day);
    }

    @PutMapping("/{id}")
    public Day updateDay(@PathVariable String id, @RequestBody Day day) {
        day.setId(id);
        return dayService.updateDay(day);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable String id) {
        dayService.deleteDay(id);
    }
}
