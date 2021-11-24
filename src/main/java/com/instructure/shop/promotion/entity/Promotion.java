package com.instructure.shop.promotion.entity;

import com.instructure.shop.course.entity.Course;
import com.instructure.shop.course.enums.CourseType;
import com.instructure.shop.promotion.enums.PromotionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table(name = "promotion")
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

  @EqualsAndHashCode.Exclude
  @Id
  @GeneratedValue
  @Column(name = "pr_id")
  private UUID id;

  /**
   * Type of promotion
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "pr_type")
  private PromotionType type;

  /**
   * Course to which promotion will be applied
   */
  @ManyToOne
  @JoinColumn(name = "cour_id", nullable = false)
  private Course course;

  /**
   * Course which should be in the card for applying. Only for
   * PromotionType.THE_CHEAPEST_COURSE_IS_FREE
   */
  @ManyToOne
  @JoinColumn(name = "cour_id_ref", nullable = false)
  private Course linkedCourse;

  /**
   * How many course should be in the card for applying free course. Only for
   * PromotionType.THE_SECOND_COURSE_IS_FREE
   */
  @Column(name = "pr_need_to_buy")
  private int needToBuy;

}
