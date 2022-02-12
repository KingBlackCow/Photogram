package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository 상속하면 Ioㅊ등록이 자동으로됨
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA query method
    User findByUsername(String username);
}
