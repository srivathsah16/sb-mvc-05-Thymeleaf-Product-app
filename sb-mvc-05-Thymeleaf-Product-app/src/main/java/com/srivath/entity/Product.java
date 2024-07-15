package com.srivath.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 3,max = 15,message = "Name should be between 3 to 15 characters")
	private String name;
	
	@NotNull(message = "Price is mandatory")
	@Positive(message = "Price must be positive number")
	private Double price;
	
	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity must be positive number")
	private Integer quantity;
}
