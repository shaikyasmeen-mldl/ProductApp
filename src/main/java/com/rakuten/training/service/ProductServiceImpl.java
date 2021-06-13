package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.training.dal.ProductDAO;
//import com.rakuten.training.dal.ProductDAOInMemImpl;
import com.rakuten.training.domain.Product;
@Service
public class ProductServiceImpl implements ProductService {
//reference to interface so use ProductDAO
	ProductDAO dao; //= new ProductDAOInMemImpl();
	@Autowired
	public void setDao(ProductDAO dao) {
		this.dao = dao;
	}
	@Override
	public int addNewProduct(Product toBeAdded) {
		if(toBeAdded.getPrice() * toBeAdded.getQoh() >= 10000) {
			Product saved = dao.save(toBeAdded);
			return saved.getId();
		}else {
			throw new IllegalArgumentException("The $ value is < 10K");
		}
	}

	@Override
	public void removeExisting(int id) {
		Product existing = dao.findById(id);
		if(existing.getPrice() * existing.getQoh() < 100000) {
			dao.deleteById(id);
		}else {
			throw new IllegalStateException("Can't delete when $ value >= 100k");
		}
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Product findById(int id) {
		return dao.findById(id);
	}
	
	

}