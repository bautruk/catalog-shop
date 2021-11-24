package com.instructure.shop.promotion.strategy;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PromotionStrategy {

  BigDecimal applyPromotionAndGetCost(Map<Course, Long> numberOfCourses,
      List<Promotion> promotions);

  default List<Promotion> getAppliedPromotions(
      List<Promotion> promotions,
      PromotionType promotionType) {

    return promotions
        .stream()
        .filter(promotion -> promotionType == promotion.getType())
        .collect(Collectors.toList());
  }
}
