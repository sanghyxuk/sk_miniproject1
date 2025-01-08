package com.backend.controller;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * - 로그인: `Post /auth/login` api 호출을 통해 닉네임으로 로그인 요청
 *          -> 성공 시 세션 생성 및 '로그인 성공' 메시지 반환
 * - 세션 상태 확인(생략): `Get /auth/session`에서 현재 로그인된 사용자 확인
 * - 닉네임 중복 확인 API: 'Get /auth/check-nickname' 호출하여 닉네임 존재 확인 여부 검토
 */

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // 로그인
    @PostMapping("/login")
    public String login(@RequestParam String nickname, HttpSession session) {
        User user = userRepository.findByNickname(nickname);

        if (user == null) {
            // 사용자 등록
            user = new User();
            user.setNickname(nickname);
            userRepository.save(user);
            return "새로운 사용자로 등록되었습니다. 닉네임: " + user.getNickname();
        }

        session.setAttribute("user", user);
        return "로그인 성공: " + user.getNickname();
    }

    // 세션 상태 확인 (x)
    @GetMapping("/session")
    public String checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "로그인 상태가 아닙니다";
        }
        return "현재 로그인 사용자: " + user.getNickname();
    }

    // 닉네임 중복 확인
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean exists = userRepository.findByNickname(nickname) != null;
        return ResponseEntity.ok(exists);
    }


}
