package com.pm.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    String street;
    String houseNumber;
    String zipCode;
}
