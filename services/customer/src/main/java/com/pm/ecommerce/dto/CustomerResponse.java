package com.pm.ecommerce.dto;

import com.pm.ecommerce.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    String id;
    String firstName;
    String lastName;
    String email;
    AddressResponse addressResponse;
}
