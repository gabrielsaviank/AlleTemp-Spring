package com.allesys.demo.controller;

import com.allesys.demo.service.AlleSysIotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    private final AlleSysIotService alleSysIotService;

    @Autowired
    public MqttController(AlleSysIotService alleSysIotService) {
        this.alleSysIotService = alleSysIotService;
    }

    @GetMapping("/mqtt/connect")
    public String connect() {
        return "Connected to MQTT!";
    }
}
