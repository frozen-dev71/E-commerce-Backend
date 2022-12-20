package com.ecommerce.Ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Admin {
    @Id
    @Min(value = 1, message = "Admin Id must be always greater than 0  !!")
    private Integer adminId;

    @NotEmpty(message = "password must not Empty or null!!")
    @Size(min = 5, max = 20, message = "Admin password should contain min 3 and max 20 chars!!")
    private String adminPassword;

}
