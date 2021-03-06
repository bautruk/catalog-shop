package com.instructure.shop.order;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrdersResponseVO;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrdersVOMapper {

  private final ModelMapper mapper = new ModelMapper();

  OrdersResponseVO toResponse(Order order) {
    OrdersResponseVO responseVO = mapper.map(order, OrdersResponseVO.class);
    responseVO.setCourseNames(
        order.getCourses()
            .stream()
            .map(Course::getType)
            .map(Enum::name)
            .collect(Collectors.toList()));
    return responseVO;
  }

  public Order toEntity(Map<Course, Integer> quantityByCourse, BigDecimal totalCostWithPromotions) {
    List<Course> courses = quantityByCourse.entrySet()
        .stream()
        .map(e -> Collections.nCopies(e.getValue(), e.getKey()))
        .flatMap(List::stream)
        .collect(Collectors.toList());
    return Order.builder()
        .courses(courses)
        .totalCost(totalCostWithPromotions)
        .build();
  }
}
