package com.backend.controller;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 모든 사용자 조회 (score DESC, createdAt ASC)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Order.desc("score"), Sort.Order.asc("createdAt")));
    }

    // 사용자 생성
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 닉네임으로 사용자 찾기
    @GetMapping("/{nickname}")
    public Optional<User> getUserByNickname(@PathVariable String nickname) {
        User user = userRepository.findByNickname(nickname);
        return Optional.ofNullable(user);
    }

    // 주변 사용자
    // 특정 사용자의 score를 기준으로 정렬된 사용자 리스트
    @GetMapping("/surround/{nickname}")
    public List<User> getSurroundingUsers(@PathVariable String nickname) {
        List<User> sortedUsers = userRepository.findAll(Sort.by(
                Sort.Order.desc("score"), Sort.Order.asc("createdAt")));

        int index = -1;
        for (int i = 0; i < sortedUsers.size(); i++) {
            if (sortedUsers.get(i).getNickname().equals(nickname)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return Collections.emptyList();
        }

        int start = Math.max(0, index - 1);
        int end = Math.min(sortedUsers.size(), index + 2);
        return sortedUsers.subList(start, end);
    }

    // 점수 업데이트 API
    @PostMapping("/updateScore")
    public String updateScore(@RequestParam String nickname, @RequestParam int score) {
        User user = userRepository.findByNickname(nickname);
        if (user != null) {
            user.setScore(user.getScore() + score); // 점수 누적
            userRepository.save(user);
            return "성공적으로 점수가 입력되었습니다.";

        } else {
            return "점수 입력에 실패하였습니다.";
        }
    }

}
