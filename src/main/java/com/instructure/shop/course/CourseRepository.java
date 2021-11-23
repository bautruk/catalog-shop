package com.instructure.shop.course;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;

import java.util.List;
import java.util.Set;

public interface CourseRepository {

  List<Course> findByTypeIn(Set<CourseType> types);

}
