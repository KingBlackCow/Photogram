package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        //AuthenticationPrincipal 세션정보 쉽게얻는방법
//        System.out.println("세션정보:"+ principalDetails.getUser());
        // 세션정보 어렵게 얻는 방법
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
//        System.out.println("직접찾은 세션정보: "+mPrincipalDetails.getUser());
        return "user/update";
    }
}
