package com.instructure.shop.promotion;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.promotion.entity.Promotion;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PromotionService {

  private final PromotionAggregator promotionAggregator;
  private final PromotionRepository promotionRepository;

  /**
   * Applying promotions to courses and return the cost.
   *
   * @param quantityByCourse Map of quantity by course
   * @return total cost with promotions
   */
  @Transactional(readOnly = true)
  public BigDecimal getTotalCostWithPromotions(Map<Course, Integer> quantityByCourse) {

    if (MapUtils.isEmpty(quantityByCourse)) {
      return BigDecimal.ZERO;
    }

    List<Promotion> promotions = promotionRepository.findAll();

    return promotionAggregator.getTheLowestCost(quantityByCourse, promotions);
  }
}
