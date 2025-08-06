package com.pm.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequest {

    String id;

    @NotBlank(message = "first name should not blank")
    @NotNull(message = "first name should not null")
    @Size(min = 2, max = 50)
    String firstName;

    @NotBlank(message = "last name should not blank")
    @NotNull(message = "last name should not null")
    @Size(min = 2, max = 50)
    String lastName;

    @NotNull(message = "email should not null")
    @Email(message = "email is not valid")
    String email;

    @Valid
    AddressRequest addressRequest;
}
