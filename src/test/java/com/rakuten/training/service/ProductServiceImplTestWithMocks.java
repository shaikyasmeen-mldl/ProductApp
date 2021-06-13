package com.rakuten.training.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector.Executable;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ProductDAOInMemImpl;
import com.rakuten.training.domain.Product;

class ProductServiceImplTestWithMocks {

	@Test
	void addNewProduct_Must_Return_NonZeroId_When_Value_GTEQ_10K() {
		//AAA
		//Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product paramToMethod = new Product("name", 10000, 1);
		
		Product saved = new Product();
		saved.setId(1);
		
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(paramToMethod)).thenReturn(saved); // training to the mock
		
		objUnderTest.setDao(mockDAO);
		//Act
		int result = objUnderTest.addNewProduct(paramToMethod);
		
		//Assert
		assertTrue(result > 0);
		
	}
	
	
	
	@Test
	void addNewProduct_Must_Throw_When_Value_LT_10K() {
		//AAA
		//Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product paramToMethod = new Product("name", 9999, 1);
		
		//Act and Assert
		
		assertThrows(IllegalArgumentException.class, () -> objUnderTest.addNewProduct(paramToMethod));
		
		
		
		
	}
	
	
	@Test
	void removeExisting_Throw() {
		//AAA
		//Arrange
		ProductServiceImpl service = new ProductServiceImpl();
		int productId = 5;
		
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Product dataReturnedByMock = new Product("test", 99999, 1);
		Mockito.when(mockDAO.findById(productId)).thenReturn(dataReturnedByMock);
		service.setDao(mockDAO);
		
		//Act
		service.removeExisting(productId);
		
		//Assert
		Mockito.verify(mockDAO).deleteById(productId);
	}
	@Test
	void removeExistingt_Must_Return_Zero_When_Value_GTEQ_100K()
	{
		//AAA
		//Arrange
		ProductServiceImpl service =new ProductServiceImpl();
		int pid=5;
		
		ProductDAO mockDAO=Mockito.mock(ProductDAO.class);
		Product dataReturnedByMock = new Product("test",99999999,1);
		Mockito.when(mockDAO.findById(pid)).thenReturn(dataReturnedByMock);
		service.setDao(mockDAO);
		
		//Act and assert
	
		assertThrows(IllegalStateException.class,()->service.removeExisting(pid));
		
		
		
	}

}

