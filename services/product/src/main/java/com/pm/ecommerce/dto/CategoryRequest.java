package com.pm.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CategoryRequest {
    @NotNull(message = "Name should not null")
    @NotBlank(message = "Name should not blank")
    private String name;

    @NotNull(message = "Description should not null")
    @NotBlank(message = "Description should not blank")
    private String description;
}
