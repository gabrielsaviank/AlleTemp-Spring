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
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
}
