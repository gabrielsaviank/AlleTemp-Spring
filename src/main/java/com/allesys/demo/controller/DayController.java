package com.allesys.demo.controller;

import com.allesys.demo.entity.Day;
import com.allesys.demo.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Optional<Day> getDayById(@PathVariable String id) {
        return dayService.getDayById(id);
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
