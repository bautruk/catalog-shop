package com.instructure.shop.order.entity;

import com.instructure.shop.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table( name = "orders" )
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

  @ManyToMany
  @JoinTable( name = "orders_course",
      joinColumns = { @JoinColumn( name = "or_id" ) },
      inverseJoinColumns = { @JoinColumn( name = "cour_id" ) } )
  private List<Course> courses;

  /**
   * Record created
   */
  @EqualsAndHashCode.Exclude
  @Column( name = "or_created", updatable = false )
  @CreatedDate
  private LocalDateTime created;

  /**
   * Record modified
   */
  @EqualsAndHashCode.Exclude
  @Column( name = "or_modified" )
  @LastModifiedDate
  private LocalDateTime modified;

}
