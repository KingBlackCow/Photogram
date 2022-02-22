package com.cos.photogramstart.config;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity //해당파일로 시큐리티를 활성화
@Configuration // IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    private final OAuth2DetailsService oAuth2DetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능 비활성화
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/user/**",
                        "/image/**",
                        "/subscribe/**",
                        "/comment/**",
                        "/api/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") //접근권한이 없으면 여기에서 로그인해라
                .loginProcessingUrl("/auth/signin")//POST -> 스프링 시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/") //로그인 완료되면  "/" 로 보내라
                .and()
                .oauth2Login() // form로그인도 하는데, oauth2로그인도 할꺼야!!
                .userInfoEndpoint() // oauth2로그인을 하면 최종응답을 회원정보를 바로 받을 수 있다.
                .userService(oAuth2DetailsService);

    }
}
