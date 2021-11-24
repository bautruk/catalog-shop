package com.instructure.shop.course;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

interface CourseRepository extends JpaRepository<Course, UUID> {
  List<Course> findAllByTypeIn(Set<CourseType> types);
}
