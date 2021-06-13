package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
@CrossOrigin
@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/products")
	public List<Product> getAllProducts(){
		return service.findAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		Product p = service.findById(id);
		if(p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}
	}

	@PostMapping("/products")
	public ResponseEntity addNewProduct(@RequestBody Product toBeAdded) {
		try {
			int id = service.addNewProduct(toBeAdded);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products/"+id));
			return new ResponseEntity(toBeAdded, headers, HttpStatus.CREATED);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable("id") int id) {
		try {
			Product p = service.findById(id);
			if(p == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}else {
				service.removeExisting(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}catch (IllegalStateException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
}
