package com.enoca.project.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.project.Business.IProductService;
import com.enoca.project.Entities.Product;

@RestController
public class ProductController {
	private IProductService productService;

	@Autowired
	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		productService.create(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable int id) {
		if (productService.getById(id) != null) {
			productService.update(product, id);
			return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Product is not found", HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		if (productService.getById(id) != null) {
			productService.deleteById(id);
			return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Product is not found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> retrieveProduct(@PathVariable int id) {
		if (productService.getById(id) != null) {
			return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
		}
		return new ResponseEntity<>("Product is not found", HttpStatus.NOT_FOUND);
	}

}
