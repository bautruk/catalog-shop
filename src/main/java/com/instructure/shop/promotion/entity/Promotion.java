package com.instructure.shop.promotion.entity;

import com.instructure.shop.course.enums.CourseType;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Promotion {

  private int needToBuy;

  private CourseType courseType;

}
