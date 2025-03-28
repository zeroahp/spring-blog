package com.spring.demo.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData {
    private String code;

    private String desc;

    private Object data;
}
