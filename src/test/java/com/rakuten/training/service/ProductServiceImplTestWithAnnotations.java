
package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTestWithAnnotations {

	@Mock
	ProductDAO mockDAO;
	
	@Test
	void addNewProduct_Must_Return_NonZeroId_When_Value_GTEQ_10K() {
	
		//AAA
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		Product param = new Product("test", 10000, 1);
		Product saved = new Product();
		saved.setId(1);
		Mockito.when(mockDAO.save(param)).thenReturn(saved);
		service.setDao(mockDAO);
		//Act
		
		int id = service.addNewProduct(param);
		//Assert
		assertTrue(id > 0);
	}

}