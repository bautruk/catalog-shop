package com.instructure.shop.promotion.strategy;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;
import com.instructure.shop.util.CourseCostCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TheSecondFreePromotionStrategy implements PromotionStrategy {

  @Override
  public BigDecimal applyPromotionAndGetCost(Map<Course, Long> numberOfCourses,
      List<Promotion> promotions) {

    Map<Course, Long> quantityByCourse = new HashMap<>(numberOfCourses);
    Map<CourseType, Course> courseTypeCourseMap = quantityByCourse.keySet().stream()
        .collect(Collectors.toMap(Course::getType, Function.identity()));

    List<Promotion> appliedPromotions =
        getAppliedPromotions(promotions, PromotionType.THE_SECOND_COURSE_IS_FREE);

    for (Promotion promotion : appliedPromotions) {
      CourseType courseType = promotion.getCourseType();
      Course course = courseTypeCourseMap.get(courseType);

      if (course != null) {
        int needToBuy = promotion.getNeedToBuy();
        long quantity = quantityByCourse.get(course);
        long countOfPossibleApplies = quantity / (needToBuy + 1);

        if (quantity != 0L && countOfPossibleApplies >= 1) {
          quantityByCourse.put(course, quantity - countOfPossibleApplies);
        }
      }
    }
    return CourseCostCalculator.getCost(quantityByCourse);
  }
}
