package com.ecommerce.Ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @NotNull(message = "Product Name is Required.")
    private String productName;

    private String description;

    @NotNull(message = "Stock value can not be null")
    @Min(value = 0, message = "Stock value must be greater than equal to 0")
    private Integer stock;

    @NotNull
    @Min(value = 1, message = "Cost must be greater than or equal to 1")
    private Double cost;
}
