package com.instructure.shop.promotion;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.strategy.PromotionStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class PromotionAggregator {

  private final PromotionStrategy[] promotionStrategies;

  /**
   * Return the cheapest cost of provided courses with apllied promotions
   *
   * @param quantityByCourse how many courses in the card by course
   * @param promotions list of available promotions
   * @return the cheapest cost for provided courses
   */
  public BigDecimal getTheLowestCost(
      Map<Course, Long> quantityByCourse,
      List<Promotion> promotions) {

    return Stream.of(promotionStrategies)
        .map(s -> s.applyPromotionAndGetCost(quantityByCourse, promotions))
        .min(Comparator.naturalOrder())
        .orElse(BigDecimal.ZERO);
  }
}
