package com.instructure.shop.promotion.mock;

import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.PromotionRepository;
import com.instructure.shop.promotion.entity.Promotion;
import com.instructure.shop.promotion.enums.PromotionType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromotionMockRepository implements PromotionRepository {

  @Override
  public List<Promotion> findCurrentPromotions() {
    return List.of(
        Promotion.builder()
            .needToBuy(1)
            .courseType(CourseType.CHEMISTRY)
            .promotionType(PromotionType.THE_SECOND_COURSE_IS_FREE)
            .build(),
        Promotion.builder()
            .needToBuy(1)
            .courseType(CourseType.MATH)
            .promotionType(PromotionType.THE_SECOND_COURSE_IS_FREE)
            .build(),
        Promotion.builder()
            .courseType(CourseType.CHEMISTRY)
            .linkedCourseType(CourseType.MATH)
            .promotionType(PromotionType.THE_CHEAPEST_COURSE_IS_FREE)
            .build()
    );
  }
}
