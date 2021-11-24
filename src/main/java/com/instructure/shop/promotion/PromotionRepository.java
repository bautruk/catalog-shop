package com.instructure.shop.promotion;

import com.instructure.shop.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PromotionRepository extends JpaRepository<Promotion, UUID> {

}
