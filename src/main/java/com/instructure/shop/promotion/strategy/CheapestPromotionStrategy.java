package com.instructure.shop.promotion.strategy;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CheapestPromotionStrategy implements PromotionStrategy {

  @Override
  public BigDecimal applyPromotionAndGetCost(Map<Course, Long> numberOfCourses,
      List<Promotion> promotions) {
    Map<Course, Long> quantityByCourse = new HashMap<>(numberOfCourses);
    Map<CourseType, Course> courseTypeCourseMap = quantityByCourse.keySet().stream()
        .collect(Collectors.toMap(Course::getType, Function.identity()));

    List<Promotion> appliedPromotions =
        getAppliedPromotions(promotions, PromotionType.THE_CHEAPEST_COURSE_IS_FREE);

    for (Promotion promotion : appliedPromotions) {
      CourseType courseType = promotion.getCourseType();
      Course course = courseTypeCourseMap.get(courseType);
      CourseType linkedCourseType = promotion.getLinkedCourseType();
      Course linkedCourse = courseTypeCourseMap.get(linkedCourseType);

      if (course == null || linkedCourse == null) {
        continue;
      }

      long number = quantityByCourse.get(course);
      long linkedNumber = quantityByCourse.get(linkedCourse);
      long countOfPossibleApplies = Math.min(number, linkedNumber);
      Course cheapestCourse = Stream.of(course, linkedCourse)
          .min(Comparator.comparing(Course::getCost))
          .orElseThrow(NoSuchElementException::new);

      if (number != 0L && countOfPossibleApplies >= 1) {
        quantityByCourse.put(cheapestCourse,
            quantityByCourse.get(cheapestCourse) - countOfPossibleApplies);
      }
    }
    return getCost(quantityByCourse);
  }
}
