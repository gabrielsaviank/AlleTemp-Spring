package com.allesys.demo.services;

import com.allesys.demo.service.TemperatureService;
import com.allesys.demo.service.TensorTemperatureModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class TensorTemperatureModelServiceTest {
    @Mock
    private TemperatureService temperatureService;
    private TensorTemperatureModelService tensorTemperatureModelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tensorTemperatureModelService = new TensorTemperatureModelService();
        tensorTemperatureModelService.temperatureService = temperatureService;
    }

    @Test
    void testPredictAverageTemperature() {
        List<Double> mockTemperatures = Arrays.asList(22.5, 23.0, 21.0, 20.5);

        when(temperatureService.getLastDayTemperatures()).thenReturn(mockTemperatures);

        float predictedAverage = tensorTemperatureModelService.predictAverageTemperature();

        System.out.println(predictedAverage);
    }
}
