package com.spring.demo.exception;
import com.spring.demo.enums.ErrorCode;
import com.spring.demo.model.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //error runtime
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(500);
        apiResponse.setMsg(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //logic error
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMsg(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        //Phương thức này lấy thông điệp lỗi mặc định từ ngoại lệ, là chuỗi được xác
        //định trong annotation của trường bị lỗi, ví dụ như "USERNAME_INVALID".
        String enumKey = exception.getFieldError().getDefaultMessage();

        //chuyển đổi chuỗi thông điệp lỗi
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMsg(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    //Error 403
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .msg(errorCode.getMessage())
                        .build()
        );
    }
}




