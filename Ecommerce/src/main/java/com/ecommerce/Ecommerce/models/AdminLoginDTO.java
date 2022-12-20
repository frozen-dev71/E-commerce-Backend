package com.ecommerce.Ecommerce.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDTO {
    private Integer adminId;
    private String password;
}
