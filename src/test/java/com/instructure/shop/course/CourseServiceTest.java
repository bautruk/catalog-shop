package com.instructure.shop.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class CourseServiceTest {

  @Autowired
  private CourseService courseService;

  @Test
  void getTotalCost_withOneValue() {
    //arrange
    var courseNames = List.of("MATH");
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(BigDecimal.valueOf(60L), totalCost);
  }
}