package com.instructure.shop.promotion;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PromotionService {

  private final PromotionAggregator promotionAggregator;
  private final PromotionRepository promotionRepository;

  /**
   * Applying promotions to courses. Apply promotions.
   *
   * @param quantityByCourse Map of number of courses
   * @return total cast with promotions
   */
  public BigDecimal getTotalCostWithPromotions(Map<Course, Long> quantityByCourse) {

    if (MapUtils.isEmpty(quantityByCourse)) {
      return BigDecimal.ZERO;
    }

    List<Promotion> promotions = promotionRepository.findCurrentPromotions();

    return promotionAggregator.getTheLowestCost(quantityByCourse, promotions);
  }
}
