package com.instructure.shop.promotion.entity;

import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.enums.PromotionType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Promotion {

  /**
   * Type of promotion
   */
  private PromotionType promotionType;

  /**
   * Type of course to which promotion will be applied
   */
  private CourseType courseType;

  /**
   * Type of course which should be in the card for applying.
   * Only for PromotionType.THE_CHEAPEST_COURSE_IS_FREE
   */
  private CourseType linkedCourseType;

  /**
   * How many course should be in the card for applying free course.
   * Only for PromotionType.THE_SECOND_COURSE_IS_FREE
   */
  private int needToBuy;

}
