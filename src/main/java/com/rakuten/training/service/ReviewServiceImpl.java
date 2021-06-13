package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewDAO reviewDAO;
	
	@Autowired
	ProductDAO productDAO;

	@Override
	public int addReviewToProduct(Review r, int productId) {
		Product p = productDAO.findById(productId);
		r.setProduct(p);
		reviewDAO.save(r);
		return r.getId();
		
	}

	@Override
	public Review findById(int id) {
		return reviewDAO.findById(id);
	}

	@Override
	public void deleteById(int id) {
		reviewDAO.deleteById(id);
	}
@Override
	public List<Review> findByPid(int productId) {
		
		return reviewDAO.findByPid(productId);
	}
	
	
}
