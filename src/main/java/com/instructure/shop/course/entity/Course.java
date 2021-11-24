package com.instructure.shop.course.entity;

import com.instructure.shop.course.enums.CourseType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Course
{

    /**
     * Type of course
     */
    private CourseType type;

    /**
     * Cost of course
     */
    private BigDecimal cost;
}
