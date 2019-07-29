package com.example.data.jdbc.exception;

import com.example.data.jdbc.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServerResponse handleResourceNotFoundException(Exception e) {
        ResourceNotFoundException exception = (ResourceNotFoundException) e;
        return ServerResponse.build(exception.getErrCode(), exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServerResponse handleUnExceptedException(Exception e) {
        log.error("GlobalExceptionHandler catch unknown exception, msg:", e);
        return ServerResponse.error("服务器内部异常");
    }

    /**
     * 通用的接口映射异常处理方法
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return new ResponseEntity(ServerResponse.build(Integer.valueOf(status.value()), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null), status);
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            log.error("参数转换失败，方法：{}，参数：{}，信息：", exception.getParameter().getMethod().getName(),
                    exception.getParameter(), exception.getLocalizedMessage());
            return new ResponseEntity(ServerResponse.error("参数转换失败"), status);
        }
        return new ResponseEntity(ServerResponse.error("参数转换失败"), status);
    }

}
