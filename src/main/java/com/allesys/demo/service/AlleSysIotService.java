package com.allesys.demo.service;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class AlleSysIotService implements MqttCallback {
    private MqttClient client;

    public AlleSysIotService() {
        try {
            this.client = new MqttClient("tcp://localhost:1883", "AlleSysClient");
            initialiseMqtt();
        } catch (MqttException exception) {
            exception.printStackTrace();
            System.out.println("Failed to initialise the MQTT client in the default constructor.");
        }
    }

    public AlleSysIotService(MqttClient client) {
        this.client = client;
        initialiseMqtt();
    }

    private void initialiseMqtt() {
        try {
            client.setCallback(this);
            client.connect();
            System.out.println("AlleSys Connected to the MQTT!");

            String topic = "currentTemperature";
            client.subscribe(topic);
            System.out.println("Subscribed to the topic " + topic);

        } catch (MqttException exception) {
            exception.printStackTrace();
            System.out.println("Failed to connect to the MQTT!");
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("AlleSy MQTT Connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("AlleSys - Message received from topic " + topic + ": " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("AlleSys - Message delivery complete");
    }
}