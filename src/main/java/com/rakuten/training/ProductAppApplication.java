package com.rakuten.training;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ProductRepository;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
		ApplicationContext springContainer = 
			SpringApplication.run(ProductAppApplication.class, args);
		
//		ProductConsoleUI ui = springContainer.getBean(ProductConsoleUI.class);
//		ui.createProductWithUI();
		
//		testReviewOps(springContainer);
		
//		testProductQueries(springContainer);
		
	//	testRepository(springContainer);
	}

	private static void testRepository(ApplicationContext springContainer) {
		ProductRepository repo = springContainer.getBean(ProductRepository.class);
		Product p = new Product("repo", 12345, 23);
		
		repo.save(p);
	}

	private static void testProductQueries(ApplicationContext springContainer) {
		ProductService service = springContainer.getBean(ProductService.class);
		List<Product> all = service.findAll();
		System.out.println("There are "+all.size()+" products:");
		all.forEach(System.out::println);
		
	}

	private static void testReviewOps(ApplicationContext springContainer) {
		//ReviewDAO dao = springContainer.getBean(ReviewDAO.class);
		ReviewService service = springContainer.getBean(ReviewService.class);
		Review aReview = new Review("my alter ego", "this is good stuff2", 5);
		service.addReviewToProduct(aReview, 3);
		//dao.save(aReview);
	}

}

	
	
	
	
	
