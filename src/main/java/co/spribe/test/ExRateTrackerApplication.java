package co.spribe.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExRateTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExRateTrackerApplication.class, args);
	}

}
