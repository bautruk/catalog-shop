package com.instructure.shop.promotion;

import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.entity.Promotion;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PromotionService {

  private final PromotionRepository promotionRepository;

  public BigDecimal applyPromotions(Map<CourseType, Long> typeNumberOfCoursesMap,
      Map<CourseType, BigDecimal> courseTypeCostMap) {

    if (MapUtils.isEmpty(typeNumberOfCoursesMap) || MapUtils.isEmpty(courseTypeCostMap))
    {
      return BigDecimal.ZERO;
    }

    List<Promotion> promotions = promotionRepository.findCurrentPromotions();
    long totalNumberOfCourses = typeNumberOfCoursesMap.values().stream().reduce(0L, Long::sum);

    for (Promotion promotion : promotions) {
      CourseType courseType = promotion.getCourseType();

      if (!typeNumberOfCoursesMap.containsKey(courseType)) {
        break;
      }

      int needToBuy = promotion.getNeedToBuy();
      long numberOfCourses = typeNumberOfCoursesMap.get(courseType);
      long countOfPossibleApplies = totalNumberOfCourses / (needToBuy + 1);
      countOfPossibleApplies = Math.min(countOfPossibleApplies, numberOfCourses);

      if (numberOfCourses != 0L && countOfPossibleApplies >= 1) {
        typeNumberOfCoursesMap.put(courseType, numberOfCourses - countOfPossibleApplies);
        totalNumberOfCourses -= (needToBuy + 1) * countOfPossibleApplies;
      }
    }
    return typeNumberOfCoursesMap.entrySet().stream()
        .map(e -> courseTypeCostMap.get(e.getKey()).multiply(BigDecimal.valueOf(e.getValue())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
