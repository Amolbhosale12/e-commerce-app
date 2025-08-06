package com.pm.ecommerce.common.utils;

import com.pm.ecommerce.common.dto.ResultDTO;


public class ResultBuilder {

    private ResultBuilder() {
    }

    public static ResultDTO success(String message) {
        return ResultDTO
                .builder()
                .message(message)
                .status("true")
                .build();
    }

    public static ResultDTO success(String message, Object data) {
        return ResultDTO
                .builder()
                .message(message)
                .status("true")
                .data(data)
                .build();
    }

    public static ResultDTO failure(String message) {
        return ResultDTO
                .builder()
                .message(message)
                .status("false")
                .build();
    }

    public static ResultDTO failure(String message, Object data) {
        return ResultDTO
                .builder()
                .message(message)
                .status("false")
                .data(data)
                .build();
    }
}
