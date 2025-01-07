package com.backend.controller;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

/**
 * - 로그인: `Post /auth/login`에 닉네임 전달
 *          -> 성공 시 세션 생성 및 '로그인 성공' 메시지 반환
 * - 세션 상태 확인: `Get /auth/session`에서 현재 로그인된 사용자 확인
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3001") // 프론트엔드 URL (혹시나 문제 생길까봐)
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // 로그인
    @PostMapping("/login")
    public String login(@RequestParam String nickname, HttpSession session) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            return "사용자를 찾을 수 없습니다.";
        }
        session.setAttribute("user", user);
        return "로그인 성공: " + user.getNickname();
    }

    // 세션 상태 확인
    @GetMapping("/session")
    public String checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "로그인 상태가 아닙니다";
        }
        return "현재 로그인 사용자: " + user.getNickname();
    }

    // 닉네임 중복 확인 API
    @GetMapping("/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        return userRepository.findByNickname(nickname) != null;
    }


}
