package com.instructure.shop.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table( name = "order" )
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @EqualsAndHashCode.Exclude
  @Id
  @GeneratedValue
  @Column( name = "or_id" )
  private UUID id;

  /**
   * Total cost of order
   */
  @NotNull
  @Column( name = "or_total_cost" )
  private BigDecimal totalCost;

}
