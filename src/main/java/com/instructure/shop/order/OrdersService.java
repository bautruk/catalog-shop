package com.instructure.shop.order;

import com.instructure.shop.order.entity.Order;
import com.instructure.shop.order.vo.OrdersResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final OrdersVOMapper ordersVOMapper;

  public OrdersResponseVO findById(UUID id) {
    Order order = ordersRepository.findById(id).orElseThrow(RuntimeException::new);
    return ordersVOMapper.toResponse(order);
  }
}
