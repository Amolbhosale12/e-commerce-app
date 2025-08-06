package com.pm.ecommerce.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotNull(message = "Name should not null")
    @NotBlank(message = "Name should not blank")
    @Size(min = 2, max = 100)
    private String name;

    @NotNull(message = "Description is not null")
    @NotBlank(message = "description should not blank")
    private String description;

    @NotNull(message = "available quantity is not null")
    private double availableQuantity;

    @NotNull(message = "price is not null")
    private BigDecimal price;

    @NotBlank(message = "category id is not blank")
    @NotNull(message = "category id is not null")
    private String categoryId;
}
