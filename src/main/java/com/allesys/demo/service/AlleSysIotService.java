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

    private AlleSysIotService(MqttClient client) {
        this.client = client;
        initialiseMqtt();
    }

    private void initialiseMqtt() {
        String topic = "dumb topic";

        try {
            client.setCallback(this);
            client.connect();
            System.out.println("AlleSys Connected to the MQTT!");
            client.subscribe(topic);
            System.out.println("Subscribed to the topic " + topic);

        } catch (MqttException exception) {
            exception.printStackTrace();
            System.out.println("Failed to connect to the MQTT!");
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("MQTT Connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message received from topic " + topic + ": " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Message delivery complete");
    }
}
