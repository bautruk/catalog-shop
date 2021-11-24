package com.instructure.shop.promotion.strategy;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;
import com.instructure.shop.util.CourseCostCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class CheapestPromotionStrategy implements PromotionStrategy {

  @Override
  public BigDecimal applyPromotionAndGetCost(Map<Course, Integer> numberOfCourses,
      List<Promotion> promotions) {
    Map<Course, Integer> quantityByCourse = new HashMap<>(numberOfCourses);
    Set<Course> courses = quantityByCourse.keySet();

    List<Promotion> appliedPromotions =
        getAppliedPromotions(promotions, PromotionType.THE_CHEAPEST_COURSE_IS_FREE);

    for (Promotion promotion : appliedPromotions) {
      Course course = promotion.getCourse();
      Course linkedCourse = promotion.getLinkedCourse();

      if (courses.contains(course) && courses.contains(linkedCourse)) {
        int quantity = quantityByCourse.get(course);
        int linkedQuantity = quantityByCourse.get(linkedCourse);
        int countOfPossibleApplies = Math.min(quantity, linkedQuantity);
        Course cheapestCourse = Stream.of(course, linkedCourse)
            .min(Comparator.comparing(Course::getCost))
            .orElseThrow(NoSuchElementException::new);

        if (quantity != 0L && countOfPossibleApplies >= 1) {
          quantityByCourse.put(cheapestCourse,
              quantityByCourse.get(cheapestCourse) - countOfPossibleApplies);
        }
      }
    }
    return CourseCostCalculator.getCost(quantityByCourse);
  }
}
