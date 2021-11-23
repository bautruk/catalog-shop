package com.instructure.shop.promotion;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.strategy.PromotionStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class PromotionFactory {

  private final PromotionStrategy[] promotionStrategies;

  public BigDecimal getTheLowestCost(
      Map<Course, Long> numberOfCourses,
      List<Promotion> promotions) {

    return Stream.of(promotionStrategies)
        .map(s -> s.applyPromotion(numberOfCourses, promotions))
        .min(Comparator.naturalOrder())
        .orElse(BigDecimal.ZERO);
  }
}
