package org.rezatron.springboot_war_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableScheduling
public class SpringbootWarTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWarTestApplication.class, args);
	}

	@Bean
MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
  return registry -> registry.config().commonTags("application", "springboot_war_test");
}

}
