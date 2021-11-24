package com.instructure.shop.course.entity;

import com.instructure.shop.course.enums.CourseType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table( name = "course" )
@NoArgsConstructor
public class Course
{

    @EqualsAndHashCode.Exclude
    @Id
    @Column( name = "cour_id" )
    private UUID id;
    /**
     * Type of course
     */
    @NotNull
    @Column( name = "cour_type" )
    private CourseType type;

    /**
     * Cost of course
     */
    @NotNull
    @Column( name = "cour_cost" )
    private BigDecimal cost;

    @EqualsAndHashCode.Exclude
    @Column( name = "cour_created", updatable = false )
    @CreatedDate
    private LocalDateTime created;

    @EqualsAndHashCode.Exclude
    @Column( name = "cour_modified" )
    @LastModifiedDate
    private LocalDateTime modified;
}
