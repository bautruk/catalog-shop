package com.instructure.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TheCourseShopApplication {

  public static void main(String[] args) {
    SpringApplication.run(TheCourseShopApplication.class, args);
  }

}
