package com.ecommerce.Ecommerce.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
