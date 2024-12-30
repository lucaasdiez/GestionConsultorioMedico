package com.mgc.response;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ApiResponse {
    private String message;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    private Object data;
}
