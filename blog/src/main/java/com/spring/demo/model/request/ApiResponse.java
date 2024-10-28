package com.spring.demo.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T> {
     private int code;
    private String msg;
    private T data;
}
