package com.instructure.shop.order;

import com.instructure.shop.order.vo.OrderRequestVO;
import com.instructure.shop.order.vo.OrderResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  /**
   * Get order by id
   *
   * @param id of order
   * @return the order {@link OrderResponseVO}
   */
  @GetMapping("/{id}")
  public ResponseEntity getOrder(@PathVariable("id") final UUID id) {
    return ResponseEntity.ok().build();
  }

  /**
   *
   *
   * @param request {@link OrderRequestVO}
   * @return
   */
  @PostMapping
  public ResponseEntity createOrder(@Valid @RequestBody OrderRequestVO request) {
    return ResponseEntity.ok().build();
  }
}
