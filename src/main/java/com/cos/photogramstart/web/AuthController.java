package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller //1.IoC 2. 파일을 리턴하는 컨틀로러
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String ,String> errorMap = new HashMap<>();
            for(FieldError error:bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성검사 실패함", errorMap);
        }
        else {
            User user = signupDto.toEntity();
            authService.회원가입(user);
            return "auth/signin";
        }
    }
}
