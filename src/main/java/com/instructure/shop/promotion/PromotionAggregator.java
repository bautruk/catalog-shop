package com.instructure.shop.promotion;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.strategy.PromotionStrategy;
import com.instructure.shop.util.CourseCostCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class PromotionAggregator {

  private final List<PromotionStrategy> promotionStrategies;

  /**
   * Return the cheapest cost of provided courses with applied promotions
   *
   * @param quantityByCourse how many courses in the card by course
   * @param promotions list of available promotions
   * @return the cheapest cost for provided courses
   */
  public BigDecimal getTheLowestCost(
      Map<Course, Integer> quantityByCourse,
      List<Promotion> promotions) {

    return promotionStrategies.stream()
        .map(s -> s.applyPromotionAndGetCost(quantityByCourse, promotions))
        .min(Comparator.naturalOrder())
        .orElse(CourseCostCalculator.getCost(quantityByCourse));
  }
}
