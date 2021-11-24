package com.instructure.shop.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrdersResponseVO {

  @NotNull
  private UUID id;

  @NotNull
  private BigDecimal totalCost;

  @NotEmpty
  private List<String> courseNames;

}
