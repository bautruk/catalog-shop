package com.instructure.shop.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

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

  @Test()
  void getTotalCost_withIncorrectValue() {
    //arrange
    var courseNames = List.of("MATH", "MATH", "PHYSICS", "MATH", "BIOLOGY");
    //assert
    assertThrows(IllegalArgumentException.class, () -> courseService.getTotalCost(courseNames));
  }

  @ParameterizedTest
  @MethodSource("provideCourseNameAndCost")
  void getTotalCost(List<String> courseNames, BigDecimal expectedCost) {
    //act
    var totalCost = courseService.getTotalCost(courseNames);
    //assert
    assertEquals(expectedCost, totalCost);
  }

  public static Stream<Arguments> provideCourseNameAndCost() {
    return Stream.of(
        Arguments.of(null, BigDecimal.ZERO),
        Arguments.of(List.of("MATH"), BigDecimal.valueOf(60L)),
        Arguments.of(List.of("MATH", "PHYSICS"), BigDecimal.valueOf(85L)),
        Arguments.of(List.of("MATH", "MATH", "PHYSICS", "MATH"), BigDecimal.valueOf(145L)),
        Arguments.of(List.of("MATH", "MATH", "MATH", "MATH"), BigDecimal.valueOf(120L)),
        Arguments.of(List.of("MATH", "PHYSICS", "MATH", "PHYSICS"), BigDecimal.valueOf(110L)),
        Arguments.of(List.of("MATH", "MATH", "MATH"), BigDecimal.valueOf(120L)),
        Arguments.of(List.of("PHYSICS", "PHYSICS", "PHYSICS"), BigDecimal.valueOf(75L)),
        Arguments.of(List.of("MATH", "CHEMISTRY", "MATH"), BigDecimal.valueOf(80L)),
        Arguments.of(List.of("CHEMISTRY", "CHEMISTRY"), BigDecimal.valueOf(20L)),
        Arguments.of(List.of("MATH", "CHEMISTRY", "CHEMISTRY"), BigDecimal.valueOf(80L)),
        Arguments.of(List.of("PHYSICS", "CHEMISTRY"), BigDecimal.valueOf(45L)),
        Arguments.of(List.of("MATH", "CHEMISTRY", "MATH", "CHEMISTRY"), BigDecimal.valueOf(80L)),
        Arguments.of(
            List.of("PHYSICS", "CHEMISTRY", "PHYSICS", "CHEMISTRY"), BigDecimal.valueOf(70L))
    );
  }
}