package com.rakuten.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ReviewService;
@CrossOrigin
@RestController
public class ReviewController {
	
	@Autowired
	ReviewService service;
	
	@PostMapping("/products/{pid}/reviews")
	public ResponseEntity addReview(@RequestBody Review toBeAdded,@PathVariable("pid") int pid) {
		int id = service.addReviewToProduct(toBeAdded, pid);
		return new ResponseEntity(toBeAdded, HttpStatus.CREATED);
	}
	
	@GetMapping("/products/{pid}/reviews")
	public List<Review> getReviewsForAProduct(@PathVariable("pid") int pid){
		return service.findByPid(pid);
	}

}
