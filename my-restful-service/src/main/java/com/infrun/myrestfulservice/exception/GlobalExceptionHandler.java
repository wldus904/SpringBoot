package com.infrun.myrestfulservice.exception;

import com.infrun.myrestfulservice.study.util.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice // 전역 예외 처리
public class GlobalExceptionHandler {
    
    // LoginService, UserDetailService
    @ExceptionHandler(value = { UserNotFoundException.class })
    public CommonResponse handle(UserNotFoundException e, WebRequest request) {
        exceptionLogging(e, request);
        
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setError(HttpStatus.BAD_REQUEST, commonResponse.getMessage());
        return commonResponse;
    }
    
    private void exceptionLogging(Exception e, WebRequest request) {
        try {
            log.error("exception: {}", e.getClass().getName());
            
            String description = request.getDescription(true);
            if(StringUtils.isEmpty(description)) {
                return;
            }
            
            String[] descriptionSplit = description.split(";");
            for (String descriptionStr:descriptionSplit) {
                log.error("Request description: {}", descriptionStr);
            }
        } catch (Exception ex) {
            // do nothing
        }
    }
}
