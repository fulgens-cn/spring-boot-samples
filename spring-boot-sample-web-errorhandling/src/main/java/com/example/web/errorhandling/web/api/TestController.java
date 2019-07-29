package com.example.web.errorhandling.web.api;

import com.example.web.errorhandling.common.ServerResponse;
import com.example.web.errorhandling.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public ServerResponse test(@RequestParam Integer num) {
        if (num % 2 == 0) {
            throw new ResourceNotFoundException("resource you request not found...");
        }
        return ServerResponse.success();
    }
}
