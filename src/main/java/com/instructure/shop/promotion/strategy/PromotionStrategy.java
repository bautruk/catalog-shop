package com.instructure.shop.promotion.strategy;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface PromotionStrategy {

  BigDecimal applyPromotion(Map<Course, Long> numberOfCourses,
      List<Promotion> promotions);

  default List<Promotion> getAppliedPromotions(
      List<Promotion> promotions,
      PromotionType promotionType) {

    return promotions
        .stream()
        .filter(promotion -> promotionType == promotion.getPromotionType())
        .collect(Collectors.toList());
  }

  default BigDecimal getCost(Map<Course, Long> numberOfCourses) {
    return numberOfCourses.entrySet().stream()
        .map(e -> e.getKey().getCost().multiply(BigDecimal.valueOf(e.getValue())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * Methods returns true if promotions can be applied to availableTypes by acceptableType
   *
   * @param promotions list of existing promotions
   * @param availableTypes list to course type to which will be apllied promotion
   * @param acceptableType acceptable type for filtering
   * @return boolean if can be applied
   */
  default boolean acceptByType(
      List<Promotion> promotions,
      Set<CourseType> availableTypes,
      PromotionType acceptableType) {

    Set<CourseType> acceptableCourseTypes = promotions.stream()
        .filter(promotion -> acceptableType == promotion.getPromotionType())
        .map(Promotion::getCourseType)
        .collect(Collectors.toSet());

    return CollectionUtils.isNotEmpty(acceptableCourseTypes) && availableTypes.stream()
        .anyMatch(acceptableCourseTypes::contains);
  }
}
