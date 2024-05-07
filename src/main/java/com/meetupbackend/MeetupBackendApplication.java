package com.meetupbackend;

import com.meetupbackend.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MeetupBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetupBackendApplication.class, args);
	}

}
