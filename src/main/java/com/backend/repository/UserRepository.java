package com.backend.repository;

import com.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User 엔티티에서 닉네임을 기준으로 사용자를 조회하는 메서드
 */

public class UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
}
