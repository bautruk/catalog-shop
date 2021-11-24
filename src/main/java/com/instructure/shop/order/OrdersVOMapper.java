package com.instructure.shop.order;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrdersResponseVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

}
