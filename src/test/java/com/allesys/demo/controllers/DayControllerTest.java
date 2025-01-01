package com.allesys.demo.controllers;

import com.allesys.demo.controller.DayController;
import com.allesys.demo.entity.Day;
import com.allesys.demo.service.DayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DayControllerTest {
    @Mock
    private DayService dayService;

    @InjectMocks
    private DayController dayController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllDays() {
        Day mockDay = new Day();
        mockDay.setId("1");
        mockDay.setCreated(new Date());
        when(dayService.getAllDays()).thenReturn(Collections.singletonList(mockDay));

        List<Day> result = dayController.getAllDays();

        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
    }

    @Test
    void testGetDayById() {
        String id = "1";
        Day mockDay = new Day();
        mockDay.setId(id);
        when(dayService.getDayById(id)).thenReturn(Optional.of(mockDay));

        ResponseEntity<Day> response = dayController.getDayById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void testGetCurrentDay() {
        Day mockDay = new Day();
        mockDay.setId("1");
        mockDay.setCreated(new Date());
        when(dayService.getCurrentDayMeasures()).thenReturn(Optional.of(mockDay));

        ResponseEntity<Day> response = dayController.getCurrentDay();

        assertEquals("1", response.getBody().getId());
    }

    @Test
    void testGetDayByDate() {
        Date currentDate = new Date();
        Day mockDay = new Day();
        mockDay.setId("1");
        mockDay.setCreated(currentDate);
        when(dayService.getDayByDate(currentDate)).thenReturn(Optional.of(mockDay));

        ResponseEntity<Day> response = dayController.getDayByDate(currentDate);

        assertEquals("1", response.getBody().getId());
        assertEquals(currentDate, response.getBody().getCreated());
    }

    @Test
    void testCreateDay() {
        Day inputDay = new Day();
        inputDay.setId("1");
        inputDay.setCreated(new Date());
        inputDay.setTemperatures(Collections.emptyList());

        Day savedDay = new Day();
        savedDay.setId("1");
        savedDay.setCreated(inputDay.getCreated());
        savedDay.setTemperatures(inputDay.getTemperatures());

        when(dayService.createDay(inputDay)).thenReturn(savedDay);

        Day result = dayController.createDay(inputDay);

        assertEquals(savedDay.getId(), result.getId());
        assertEquals(savedDay.getCreated(), result.getCreated());
        assertEquals(savedDay.getTemperatures(), result.getTemperatures());
    }

    @Test
    void testUpdateDay() {
        String id = "1";
        Day inputDay = new Day();
        inputDay.setCreated(new Date());
        inputDay.setTemperatures(Collections.emptyList());

        Day updatedDay = new Day();
        updatedDay.setId(id);
        updatedDay.setCreated(inputDay.getCreated());
        updatedDay.setTemperatures(inputDay.getTemperatures());

        when(dayService.updateDay(inputDay)).thenReturn(updatedDay);

        Day result = dayController.updateDay(id, inputDay);

        assertEquals(id, result.getId());
        assertEquals(updatedDay.getCreated(), result.getCreated());
        assertEquals(updatedDay.getTemperatures(), result.getTemperatures());
    }

    @Test
    void testDeleteDay() {
        String id = "1";

        dayController.deleteDay(id);

        verify(dayService).deleteDay(id);
    }
}
