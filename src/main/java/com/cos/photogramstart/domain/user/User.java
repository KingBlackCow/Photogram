package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @Column(length = 20, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진
    private String role;//권한
    private LocalDateTime createDate;

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마
    // User를 Select 할 때 해당 User id로 등록된 image들을 다가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Image> images;

    @PrePersist//디비에 Insert 되기 직전에 실행
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
