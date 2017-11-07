package com.example.data.combine;

import com.example.data.combine.controller.PostgresController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages = "com.example.data.combine")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.data.combine.postgres.model"})
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
		basePackages = {"com.example.data.combine.postgres"})
@EnableMongoRepositories(basePackages = {"com.example.data.combine.mongo"})
@EnableJpaAuditing
@EnableMongoAuditing
@EnableAsync
public class CombineApplication {
	public static void main(String[] args) {
		final Logger LOG = LoggerFactory.getLogger(CombineApplication.class);
		LOG.info("application ready to start ... :");
		SpringApplication.run(CombineApplication.class, args);
		LOG.info("application successfully started ... :");
	}
}
