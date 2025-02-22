package com.allesys.demo.controllers;

import com.allesys.demo.controller.TemperatureController;
import com.allesys.demo.service.TemperatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TemperatureControllerTest {
    @Mock
    private TemperatureService temperatureService;

    @InjectMocks
    private TemperatureController temperatureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTemperatures() {

    }

    @Test
    void testGetTemperatureById() {

    }

    @Test
    void testGetLastDayTemperatures() {

    }

    @Test
    void testCreateMeasure() {

    }

    @Test
    void testUpdateMeasure() {

    }

    @Test
    void testDeleteMeasure() {

    }
}
