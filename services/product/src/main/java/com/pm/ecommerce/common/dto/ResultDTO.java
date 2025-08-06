package com.pm.ecommerce.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ResultDTO {
    private String message;
    private String status;
    private Object data;
}
