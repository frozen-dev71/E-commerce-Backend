package com.ecommerce.Ecommerce.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private List<Orders> list = new ArrayList<>();

    private Double totalBill;
}
