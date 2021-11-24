package com.instructure.shop.order.vo;

import lombok.Data;

import java.util.List;
import javax.validation.constraints.NotEmpty;

@Data
public class OrdersRequestVO {

  @NotEmpty
  private List<String> courseNames;

}
