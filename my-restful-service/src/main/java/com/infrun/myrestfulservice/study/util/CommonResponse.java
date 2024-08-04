package com.infrun.myrestfulservice.study.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드 제거
public class CommonResponse {
    private String result;
    private int status = HttpStatus.OK.value();
    private Integer errorCode;
    private String message;
    private Object paging;
    private Object data;

    public CommonResponse(Object data) {
        setData(data);
    }

    public void setError(HttpStatus httpStatus, String message) {
        setResult("ERROR");
        setStatus(httpStatus.value());
        setMessage(message);
    }

    public void setError(HttpStatus httpStatus, List<FieldError> fieldErrors) {
        setResult("ERROR");
        setStatus(httpStatus.value());

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error:fieldErrors) {
            stringBuilder.append(error.getField()).append("-").append(error.getDefaultMessage());
        }

        setMessage(stringBuilder.toString());
    }
}
