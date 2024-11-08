package com.allesys.demo.services;

import com.allesys.demo.entity.Day;
import com.allesys.demo.repository.DayRepository;
import com.allesys.demo.service.DayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DayServiceTest {
    @Mock
    private DayRepository dayRepository;

    @InjectMocks
    private DayService dayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDays() {
        Day dayOne = new Day();
        dayOne.setId("121416");
        dayOne.setCreated(new Date());

        Day dayTwo = new Day();
        dayTwo.setId("121417");
        dayTwo.setCreated(new Date());

        when(dayRepository.findAll())
                .thenReturn(Arrays.asList(dayOne, dayTwo));

        List<Day> result = dayService.getAllDays();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("121416", result.get(0).getId());
        assertEquals("121417", result.get(1).getId());

        verify(dayRepository, times(1)).findAll();
    }

    @Test
    void getDayById() {
        Day day = new Day();

        day.setId("121416");

        when(dayRepository.findById("121416"))
                .thenReturn(Optional.of(day));

        Optional<Day> result = dayService.getDayById("121416");

        assertTrue(result.isPresent());
        assertEquals("121416", result.get().getId());
    }

    @Test
    void getCurrentDayMeasures() {
        Day currentDay = new Day();
        currentDay.setId("121416");
        currentDay.setCreated(new Date());

        when(dayRepository.findFirstByOrderByCreatedDesc())
                .thenReturn(Optional.of(currentDay));

        Optional<Day> result = dayService.getCurrentDayMeasures();

        assertTrue(result.isPresent());
        assertEquals("121416", result.get().getId());
        assertNotNull(result.get().getCreated());

        verify(dayRepository, times(1)).findFirstByOrderByCreatedDesc();
    }

    @Test
    void geDayByDate() throws Exception {
        Day day = new Day();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = sdf.parse("2024-11-02T10:00:00.000+00:00");
        day.setCreated(date);

        DayRepository dayRepository = mock(DayRepository.class);
        when(dayRepository.findByCreated(date)).thenReturn(Optional.of(day));

        DayService dayService = new DayService(dayRepository);

        Optional<Day> result = dayService.getDayByDate(date);

        assertTrue(result.isPresent());
        assertEquals(date, result.get().getCreated());
    }


    @Test
    void createDay() {
        Day newDay = new Day();
        newDay.setId("1");
        newDay.setCreated(new Date());

        when(dayRepository.save(any(Day.class))).thenReturn(newDay);

        Day result = dayService.createDay(newDay);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertNotNull(result.getCreated());

        verify(dayRepository, times(1)).save(newDay);
    }
}
