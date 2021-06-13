package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewService {

	int addReviewToProduct(Review r, int productId);

	Review findById(int id);

	void deleteById(int id);
  public List<Review> findByPid(int productId);

}
