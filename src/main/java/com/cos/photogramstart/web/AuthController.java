package com.cos.photogramstart.web;

import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //1.IoC 2. 파일을 리턴하는 컨틀로러
@Slf4j
public class AuthController {

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto){
        log.info(String.valueOf(signupDto));
        return "auth/signin";
    }
}
