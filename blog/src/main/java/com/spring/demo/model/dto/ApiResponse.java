package com.spring.demo.model.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) //eliminate null
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;
}
