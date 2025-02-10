package com.ruturaj.springboot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruturaj.springboot.entity.Product;
import com.ruturaj.springboot.entity.User;
import com.ruturaj.springboot.repository.ProductRepository;
import com.ruturaj.springboot.repository.UserRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	public Product addProduct(Product product, int userId) {
		Product addedProduct = productRepository.save(product);
		User user = userRepository.findById(userId).get();
		List<Product> products = user.getProducts();
		products.add(addedProduct);
		user.setProducts(products);
		userRepository.save(user);
		return addedProduct;
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public Product deleteProduct(int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			productRepository.delete(product.get());
			return product.get();
		}
		return null;

	}

	public List<Product> findProductsByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> sortProductsByCategory(int sortBy) {
		List<Product> products = productRepository.findAll();
		Collections.sort(products);
		if (sortBy == 1)
			Collections.reverse(products);
		return products;
	}

	public List<Product> findProductsByQuery(String query) {
		return productRepository.findByQuery(query);
	}

}