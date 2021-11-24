package com.instructure.shop.order;

import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrderResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderVOMapper orderVOMapper;

  public OrderResponseVO findById(UUID id) {
    Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
    return orderVOMapper.toResponse(order);
  }
}
