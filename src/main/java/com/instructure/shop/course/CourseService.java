package com.instructure.shop.course;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  public BigDecimal getTotalCost(List<String> courseNames) {
    if (CollectionUtils.isEmpty(courseNames)) {
      return BigDecimal.ZERO;
    }
    Map<CourseType, Long> typeNumberOfCoursesMap = courseNames.stream().map(CourseType::valueOf)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

    Map<CourseType, BigDecimal> courseTypeCostMap = getCostOfCourses(
        typeNumberOfCoursesMap.keySet());
    return typeNumberOfCoursesMap.entrySet().stream()
        .map(e -> courseTypeCostMap.get(e.getKey()).multiply(BigDecimal.valueOf(e.getValue())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private Map<CourseType, BigDecimal> getCostOfCourses(Set<CourseType> courseTypes) {
    List<Course> courses = courseRepository.findByTypeIn(courseTypes);
    return courses
        .stream()
        .collect(Collectors.toMap(Course::getType, Course::getCost));
  }
}
