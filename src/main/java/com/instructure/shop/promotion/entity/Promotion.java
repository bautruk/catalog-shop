package com.instructure.shop.promotion.entity;

import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.enums.PromotionType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Promotion {

  private PromotionType promotionType;

  private CourseType courseType;

  private CourseType linkedCourseType;

  private int needToBuy;

}
