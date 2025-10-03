package com.pm.ecommerce.common.utils;

import com.pm.ecommerce.common.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResultBuilder {


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
                .data(data)
                .status("true")
                .build();
    }

    public static ResultDTO error(String message) {

        return ResultDTO
                .builder()
                .message(message)
                .status("false")
                .build();
    }

    public static ResultDTO error(String message, Object data) {

        return ResultDTO
                .builder()
                .message(message)
                .data(data)
                .status("false")
                .build();
    }
}
