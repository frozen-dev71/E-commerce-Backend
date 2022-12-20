package com.ecommerce.Ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
