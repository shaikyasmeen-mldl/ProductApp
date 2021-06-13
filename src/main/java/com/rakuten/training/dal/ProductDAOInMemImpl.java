
package com.rakuten.training.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rakuten.training.domain.Product;
@Repository
public class ProductDAOInMemImpl implements ProductDAO {

	Map<Integer,Product> map = new HashMap<>();
	int idSequence = 0;
	@Override
	public Product save(Product toBeSaved) {
		int id = ++idSequence;
		toBeSaved.setId(id);
		map.put(id, toBeSaved);
		return toBeSaved;
	}
	@Override
	public List<Product> findAll() {
		return new ArrayList<>(map.values());

	}
	
	@Override
	public Product findById(int id) {
		return map.get(id);

	}
	
	@Override
	public void deleteById(int id) {

		map.remove(id);
		
	}
	
	
	
}