package org.rezatron.springboot_war_test.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@Service
@Slf4j
public class MyScheduledTask {

    private final RestTemplate restTemplate;
    private final Random random = new Random();

    @Autowired
    public MyScheduledTask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // This method runs at a random interval between 1 to 15 seconds
    @Scheduled(fixedRateString = "#{1000 + new java.util.Random().nextInt(15000)}") // between 1 and 15 seconds
    public void runRandomApiCalls() {
        // Randomly skip execution with a 10% probability
        if (random.nextDouble() < 0.1) {
            log.info("Skipping this execution cycle.");
            return;
        }

        log.info("Scheduled task started: making a randomized API request.");

        String url = "http://192.168.104.213:8080/springboot-war-test/api";
        try {
            // Randomly simulate a failure in making the API request
            if (random.nextDouble() < 0.05) {
                throw new RuntimeException("Simulated API call failure.");
            }

            // Make GET request
            String response = restTemplate.getForObject(url, String.class);

            // Randomly log different levels of responses
            if (random.nextBoolean()) {
                log.debug("Detailed response from API: {}", response);
            } else {
                log.info("Shortened response from API: {}", response.length() > 100 ? response.substring(0, 100) + "..." : response);
            }

        } catch (Exception e) {
            log.error("Error while making the GET request to {}: {}", url, e.getMessage());
        }

        log.info("Scheduled task finished.");
    }
}