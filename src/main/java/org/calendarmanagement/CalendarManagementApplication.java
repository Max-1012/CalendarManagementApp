package org.calendarmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CalendarManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarManagementApplication.class, args);
    }

}
