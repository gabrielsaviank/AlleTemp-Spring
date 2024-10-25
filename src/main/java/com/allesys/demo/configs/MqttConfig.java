package com.allesys.demo.configs;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Bean
    public MqttClient mqttClient() throws MqttException {
        String brokerUri = "tcp://91.121.93.94:1883";
        String clientId = "AlleSysClient";

        MqttClient mqttClient = new MqttClient(brokerUri, clientId);
        return mqttClient;
    }
}