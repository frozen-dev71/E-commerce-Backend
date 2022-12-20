package com.ecommerce.Ecommerce.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Product Name is Required.")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String productName;

    private String description;

    @NotNull(message = "Stock value can not be null")
    @Min(value = 0, message = "Stock value must be greater than or equal to 0")
    private Integer stock;

    @NotNull
    @Min(value = 1, message = "Cost must be greater than or equal to 1")
    private Double cost;
}
