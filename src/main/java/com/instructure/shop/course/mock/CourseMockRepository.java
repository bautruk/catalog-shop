package com.instructure.shop.course.mock;

import com.instructure.shop.course.CourseRepository;
import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CourseMockRepository implements CourseRepository {

  @Override
  public List<Course> findByTypeIn(Set<CourseType> types) {
    return types.stream().map(this::getCourse).collect(Collectors.toList());
  }

  private Course getCourse(CourseType type) {
    return switch (type) {
      case MATH -> Course.builder().type(CourseType.MATH).cost(BigDecimal.valueOf(60L)).build();
      case PHYSICS -> Course.builder().type(CourseType.PHYSICS).cost(BigDecimal.valueOf(25L))
          .build();
      default -> throw new IllegalArgumentException();
    };
  }
}
