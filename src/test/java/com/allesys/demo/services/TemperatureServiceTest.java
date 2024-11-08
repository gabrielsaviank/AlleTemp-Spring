package com.allesys.demo.services;

import com.allesys.demo.entity.Day;
import com.allesys.demo.entity.Temperature;
import com.allesys.demo.repository.DayRepository;
import com.allesys.demo.repository.TemperatureRepository;
import com.allesys.demo.service.TemperatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureServiceTest {
    @Mock
    private TemperatureRepository temperatureRepository;

    @Mock
    private DayRepository dayRepository;

    @InjectMocks
    private TemperatureService temperatureService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTemperatureById() {
        Temperature temperature = new Temperature();
        temperature.setId("123");
        when(temperatureRepository.findById("123"))
                .thenReturn(Optional.of(temperature));


        Optional<Temperature> result = temperatureService.getTemperatureById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
    }

    @Test
    void testCreateMeasure() {
        Temperature temperature = new Temperature();
        Day day = new Day();
        // This is equivalent to sinons
        when(temperatureRepository.save(any(Temperature.class))).thenReturn(temperature);
        when(dayRepository.findFirstByOrderByCreatedDesc()).thenReturn(Optional.of(day));
        when(dayRepository.save(any(Day.class))).thenReturn(day);

        Temperature createdTemperature = temperatureService.createMeasure(20.5);

        assertNotNull(createdTemperature);
        verify(temperatureRepository, times(1)).save(any(Temperature.class));
        verify(dayRepository, times(1)).save(any(Day.class));
    }

    @Test
    void getLastDayTemperatures() {
        Temperature tempOne = new Temperature();
        tempOne.setMeasure(20.5);
        tempOne.setTime(new Date(System.currentTimeMillis() - 2 * 60 * 60 * 1000)); // 2 hours ago

        Temperature tempTwo = new Temperature();
        tempTwo.setMeasure(21.5);
        tempTwo.setTime(new Date(System.currentTimeMillis() - 3 * 60 * 60 * 1000)); // 3 hours ago

        when(temperatureRepository.findByTimeAfterOrderByTimeDesc(any(Date.class)))
                .thenReturn(Arrays.asList(tempOne, tempTwo));

        List<Double> result = temperatureService.getLastDayTemperatures();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(20.5, result.get(0));
        assertEquals(21.5, result.get(1));

        verify(temperatureRepository, times(1)).findByTimeAfterOrderByTimeDesc(any(Date.class));
    }
}
