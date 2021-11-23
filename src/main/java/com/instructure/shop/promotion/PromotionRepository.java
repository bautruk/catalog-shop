package com.instructure.shop.promotion;

import com.instructure.shop.promotion.entity.Promotion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository {

  List<Promotion> findCurrentPromotions();

}
