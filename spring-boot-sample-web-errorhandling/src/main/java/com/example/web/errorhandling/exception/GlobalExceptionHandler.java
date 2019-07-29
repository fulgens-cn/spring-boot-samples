package com.example.web.errorhandling.exception;

import com.example.web.errorhandling.common.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleCustomException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ResourceNotFoundException exception = (ResourceNotFoundException) e;
        if (isAjax(request)) {
            ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
            ServerResponse serverResponse = ServerResponse.build(exception.getErrCode(), exception.getMessage(), null);
            modelAndView.addObject(serverResponse);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setStatus(getStatus(request));
            modelAndView.addObject("path", request.getRequestURI());
            modelAndView.addObject("message", exception.getMessage());
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    /**
     * 判断请求是否为ajax请求
     *
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * 返回错误json响应
     *
     * @param response
     * @param serverResponse
     */
    private void writeErrorMsg(HttpServletResponse response, ServerResponse serverResponse) {
        try (OutputStream os = response.getOutputStream()) {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            os.write(objectMapper.writeValueAsBytes(serverResponse));
            os.flush();
        } catch (IOException e) {
            log.error("返回错误json响应异常，异常信息： {}", e);
        }
    }

    /**
     * 获取http状态码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
