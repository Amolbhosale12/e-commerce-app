package com.pm.ecommerce.common.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private String message;
    private String status;
    private Object data;
}
