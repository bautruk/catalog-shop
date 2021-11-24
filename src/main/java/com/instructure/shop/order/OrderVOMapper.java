package com.instructure.shop.order;

import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrderResponseVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderVOMapper {

  private final ModelMapper mapper = new ModelMapper();

  OrderResponseVO toResponse(Order order) {
    return mapper.map(order, OrderResponseVO.class);
  }

}
