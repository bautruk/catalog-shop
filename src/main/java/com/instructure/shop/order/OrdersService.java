package com.instructure.shop.order;

import com.instructure.shop.course.CourseService;
import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrdersRequestVO;
import com.instructure.shop.order.vo.OrdersResponseVO;
import com.instructure.shop.promotion.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder.In;

@Service
@AllArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final OrdersVOMapper ordersVOMapper;
  private final PromotionService promotionService;
  private final CourseService courseService;

  @Transactional(readOnly = true)
  public OrdersResponseVO findById(UUID id) {
    Order order = ordersRepository.findById(id).orElseThrow(RuntimeException::new);
    return ordersVOMapper.toResponse(order);
  }

  @Transactional
  public OrdersResponseVO create(OrdersRequestVO request) {
    Map<CourseType, Integer> quantityByCourseType = request.getCourseNames()
        .stream()
        .map(CourseType::valueOf)
        .collect(Collectors.groupingBy(c -> c, Collectors.summingInt(c -> 1)));

    List<Course> courses = courseService.findByTypeIn(quantityByCourseType.keySet());

    Map<CourseType, Course> courseByCourseType = courses.stream()
        .collect(Collectors.toMap(Course::getType, Function.identity()));

    Map<Course, Integer> quantityByCourse = quantityByCourseType.entrySet()
        .stream()
        .collect(Collectors.toMap(k -> courseByCourseType.get(k.getKey()), Entry::getValue));

    BigDecimal totalCostWithPromotions =
        promotionService.getTotalCostWithPromotions(quantityByCourse);
    Order order = ordersVOMapper.toEntity(quantityByCourse, totalCostWithPromotions);
    ordersRepository.save(order);
    return ordersVOMapper.toResponse(order);
  }
}
