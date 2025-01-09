package com.backend.controller;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/rankings")
    public ResponseEntity<List<User>> getRankings() {
        // 점수 기준으로 정렬된 사용자 목록 반환
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/rank")
    public ResponseEntity<Integer> getRank(String nickname) {
        // 점수 기준으로 정렬된 사용자 목록
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));

        // 주어진 닉네임의 사용자 등수 계산
        int rank = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNickname().equals(nickname)) {
                rank = i + 1; // 등수는 1부터 시작
                break;
            }
        }

        return ResponseEntity.ok(rank);
    }
}
