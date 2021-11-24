package com.instructure.shop.course;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  private final PromotionService promotionService;

  /**
   * Count total cost of courses
   *
   * @param courseNames list of course name
   * @return total cost with promotions
   */
  public BigDecimal getTotalCost(List<String> courseNames) {
    if (CollectionUtils.isEmpty(courseNames)) {
      return BigDecimal.ZERO;
    }
    Map<CourseType, Long> quantityByCourseType = courseNames.stream().map(CourseType::valueOf)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

    List<Course> courses = courseRepository.findByTypeIn(quantityByCourseType.keySet());

    Map<CourseType, Course> courseByCourseType = courses.stream()
        .collect(Collectors.toMap(Course::getType, Function.identity()));

    Map<Course, Long> quantityByCourse = quantityByCourseType.entrySet()
        .stream()
        .collect(Collectors.toMap(k -> courseByCourseType.get(k.getKey()), Entry::getValue));

    return promotionService.getTotalCostWithPromotions(quantityByCourse);
  }
}
