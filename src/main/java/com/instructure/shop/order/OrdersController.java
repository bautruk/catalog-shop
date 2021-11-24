package com.instructure.shop.order;

import com.instructure.shop.order.vo.OrdersRequestVO;
import com.instructure.shop.order.vo.OrdersResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

  private final OrdersService ordersService;

  /**
   * Get order by id
   *
   * @param id of order
   * @return the order {@link OrdersResponseVO}
   */
  @GetMapping("/{id}")
  public @ResponseBody OrdersResponseVO getOrder(@PathVariable("id") final UUID id) {
    return ordersService.findById(id);
  }

  /**
   * Creating order with list of courses and the total cost
   *
   * @param request {@link OrdersRequestVO}
   * @return list of course names and total cost {@link OrdersResponseVO}
   */
  @PostMapping
  public @ResponseBody OrdersResponseVO createOrder(@Valid @RequestBody OrdersRequestVO request) {
    throw new UnsupportedOperationException();
  }
}
