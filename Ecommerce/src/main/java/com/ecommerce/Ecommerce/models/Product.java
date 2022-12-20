package com.ecommerce.Ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
