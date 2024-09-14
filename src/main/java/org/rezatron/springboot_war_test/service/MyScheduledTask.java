package org.rezatron.springboot_war_test.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class MyScheduledTask {

    private final RestTemplate restTemplate;

    @Autowired
    public MyScheduledTask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // This method will run every 5 seconds
    @Scheduled(fixedRate = 5000) // 900000 milliseconds = 5 seconds
    public void runEveryFifteenMinutes() {
        log.info("Scheduled task started: running every 5 seconds.");

        // Make GET request
        String url = "http://192.168.104.213:8080/springboot-war-test/api";
        try {
            String response = restTemplate.getForObject(url, String.class);
            log.info("Received response from API: {}", response);
        } catch (Exception e) {
            log.error("Error while making the GET request to {}: {}", url, e.getMessage());
        }

        log.info("Scheduled task finished.");
    }
}