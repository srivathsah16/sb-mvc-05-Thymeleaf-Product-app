package com.srivath.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srivath.entity.Product;
import com.srivath.repo.ProductRepo;

import jakarta.validation.Valid;

@Controller
public class ProductController {

	@Autowired
	private ProductRepo repo;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}

	@PostMapping("/save")
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "index";
		}
		product = repo.save(product);
		if (product.getId() != null) {
			model.addAttribute("message", "Product saved in Database!");
		}
		return "index";
	}

	@GetMapping("/viewAll")
	public String getProducts(Model model) {
		System.out.println("getProducts method triggered");
		model.addAttribute("products", repo.findAll());
		return "data";
	}

	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("pid") Integer productId, Model model) {
		if (repo.findById(productId).isPresent()) {
			repo.deleteById(productId);
			model.addAttribute("msg", "Product deleted successfully!!!");
		}
		model.addAttribute("products", repo.findAll());
		return "data";
	}

	@GetMapping("/edit")
	public String editProduct(@RequestParam("pid") Integer pid, Model model) {
		Optional<Product> optionl = repo.findById(pid);
		if (optionl.isPresent()) {
			Product product = optionl.get();
			model.addAttribute("product", product);
		}
		model.addAttribute("updateMsg", "Edit the details and click on save button!");
		return "index";
	}
}
