package com.instructure.shop.course.entity;

import com.instructure.shop.course.enums.CourseType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Course
{
    private CourseType type;

    private BigDecimal cost;
}
