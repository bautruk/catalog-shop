package com.instructure.shop.order;

import com.instructure.shop.order.vo.OrderRequestVO;
import com.instructure.shop.order.vo.OrderResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;

  /**
   * Get order by id
   *
   * @param id of order
   * @return the order {@link OrderResponseVO}
   */
  @GetMapping("/{id}")
  public OrderResponseVO getOrder(@PathVariable("id") final UUID id) {
    return orderService.findById(id);
  }

  /**
   * Creating order with list of courses and the total cost
   *
   * @param request {@link OrderRequestVO}
   * @return list of course names and total cost {@link OrderResponseVO}
   */
  @PostMapping
  public OrderResponseVO createOrder(@Valid @RequestBody OrderRequestVO request) {
    throw new UnsupportedOperationException();
  }
}
