package com.ecommerce.Ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @NotEmpty
    @Size(min = 3, message = "Customer Name should contain 3 or more letters.")
    private String name;

    @NotEmpty
    @Size(min = 10, max = 10, message = "mobile Number  must be exact 10 digit !!")
    @Digits(fraction = 0, integer = 10, message = "Mobile Number should only contains with numbers.")
    private String mobileNum;

    @NotEmpty
    @Size(min = 5, max = 15, message = "Customer password should contain minimum 5 and maximum 15 characters.")
    private String password;

    @NotEmpty
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please Enter valid Email Id included @ and proper Name !!")
    private String email;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
