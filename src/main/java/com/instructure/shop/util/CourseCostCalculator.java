package com.instructure.shop.util;

import com.instructure.shop.course.entity.Course;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseCostCalculator {

  public static BigDecimal getCost(Map<Course, Integer> numberOfCourses) {
    return numberOfCourses.entrySet().stream()
        .map(e -> e.getKey().getCost().multiply(BigDecimal.valueOf(e.getValue())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
