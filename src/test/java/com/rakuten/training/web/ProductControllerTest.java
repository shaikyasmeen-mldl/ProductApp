//This is incontainer testing not out of containers.
//Here we are using Spring
package com.rakuten.training.web;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest {

	@MockBean
	ProductService mockService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testGetById() throws Exception {
		//AAA
		//Arrange
		int id = 5;
		Product dataReturnedByMock = new Product("test", 10000, 1);
		dataReturnedByMock.setId(id);
		Mockito.when(mockService.findById(id)).thenReturn(dataReturnedByMock);
		
		//Act and Assert
		mockMvc
			.perform(MockMvcRequestBuilders.get("/products/{id}", id))
			.andExpect(MockMvcResultMatchers.status().is(200))
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id)));
		
	}

}
