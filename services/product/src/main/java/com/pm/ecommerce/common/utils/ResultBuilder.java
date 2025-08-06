package com.pm.ecommerce.common.utils;

import com.pm.ecommerce.common.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResultBuilder {


    public static ResponseEntity<ResultDTO> success(String message) {

        ResultDTO resultDTO = ResultDTO
                .builder()
                .message(message)
                .status("true")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    public static ResponseEntity<ResultDTO> success(String message, Object data) {

        ResultDTO resultDTO = ResultDTO
                .builder()
                .message(message)
                .data(data)
                .status("true")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(resultDTO);
    }

    public static ResponseEntity<ResultDTO> error(String message) {

        ResultDTO resultDTO = ResultDTO
                .builder()
                .message(message)
                .status("false")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO);
    }

    public static ResponseEntity<ResultDTO> error(String message, Object data) {

        ResultDTO resultDTO = ResultDTO
                .builder()
                .message(message)
                .data(data)
                .status("false")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultDTO);
    }
}
