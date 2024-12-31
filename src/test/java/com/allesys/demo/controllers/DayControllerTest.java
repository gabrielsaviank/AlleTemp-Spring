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
}
