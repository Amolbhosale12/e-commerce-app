package com.pm.ecommerce.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO {
    private String message;
    private String status;
    private Object data;
}
