package com.pm.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank(message = "street should not blank")
    @NotNull(message = "street should not null")
    private String street;

    @NotBlank(message = "house number should not black")
    private String houseNumber;

    @NotBlank(message = "zip code is required")
    @Pattern(regexp = "\\d{5}", message = "zipcode must be 5 digits")
    private String zipCode;
}
