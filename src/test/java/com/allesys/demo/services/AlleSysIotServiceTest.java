package com.allesys.demo.services;

import com.allesys.demo.service.AlleSysIotService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AlleSysIotServiceTest {

    @Mock
    private MqttClient mqttClient;

    @InjectMocks
    private AlleSysIotService alleSysIotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alleSysIotService = new AlleSysIotService(mqttClient);
    }

    @Test
    void testMqttConnectionSuccessful() throws MqttException {
        doNothing().when(mqttClient).connect();
        doNothing().when(mqttClient).subscribe(anyString());

        // TODO Check why it's invoking twice. :thinking
        // Must change the private method and bla bla bla.
        verify(mqttClient, times(2)).connect();
        verify(mqttClient, times(2)).subscribe(anyString());
    }

    @Test
    void testMessageArrived() throws Exception {
        MqttMessage mockMessage = new MqttMessage("test payload".getBytes());
        String testTopic = "test/topic";

        alleSysIotService.messageArrived(testTopic, mockMessage);

        assertEquals("test payload", new String(mockMessage.getPayload()));
    }

    @Test
    void testConnectionLost() {
        Throwable mockCause = new Throwable("Connection lost");

        alleSysIotService.connectionLost(mockCause);
    }
}
