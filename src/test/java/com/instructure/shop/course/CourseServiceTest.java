package com.instructure.shop.course;

import static org.junit.jupiter.api.Assertions.*;

import com.instructure.shop.course.enums.CourseType;
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
  void getTotalCost_withNull() {
    //arrange
    List<String> courseNames = null;
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(BigDecimal.valueOf(0), totalCost);
  }

  @Test
  void getTotalCost_withOneValue() {
    //arrange
    var courseNames = List.of("MATH");
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(BigDecimal.valueOf(60L), totalCost);
  }

  @Test
  void getTotalCost_withTwoValue() {
    //arrange
    var courseNames = List.of("MATH", "PHYSICS");
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(BigDecimal.valueOf(85L), totalCost);
  }

  @Test
  void getTotalCost_withFourValue() {
    //arrange
    var courseNames = List.of("MATH", "MATH", "PHYSICS", "MATH");
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(BigDecimal.valueOf(205L), totalCost);
  }

  @Test()
  void getTotalCost_withIncorrectValue() {
    //arrange
    var courseNames = List.of("MATH", "MATH", "PHYSICS", "MATH", "BIOLOGY");
    //assert
    assertThrows(IllegalArgumentException.class, () -> courseService.getTotalCost(courseNames));
  }
}