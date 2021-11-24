package com.instructure.shop.course;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional(readOnly = true)
  public List<Course> findByTypeIn(Set<CourseType> types) {
    return courseRepository.findAllByTypeIn(types);
  }
}
