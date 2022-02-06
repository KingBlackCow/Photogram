package com.cos.photogramstart.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다.
    private int id;
    private String username;
    private String password;
    private String name;
    private String website;
    private String bio; //자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진
    private String role;//권한
   private LocalDateTime createDate;

    @PrePersist//디비에 Insert 되기 직전에 실행
    private void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
