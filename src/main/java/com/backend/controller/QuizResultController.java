package com.backend.controller;

//데이터를 저장하는 메서드

import com.backend.dto.QuizResultDTO;
import com.backend.entity.User;
import com.backend.entity.QuizResult;
import com.backend.repository.UserRepository;
import com.backend.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date; // Date class 사용

@RestController
@RequestMapping("/api/quiz")
public class QuizResultController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @PostMapping("/saveResult")
    public String saveQuizResult(@RequestBody QuizResultDTO quizResultDTO) {
        // 닉네임으로 사용자 검색
        User user = userRepository.findByNickname(quizResultDTO.getNickname());
        if (user != null) {
            // QuizResult 생성 및 저장
            QuizResult quizResult = new QuizResult();
            quizResult.setUser(user);
            quizResult.setScore(quizResultDTO.getScore());
            quizResult.setCompletedAt(new Date());
            quizResultRepository.save(quizResult);

            return "데이터를 성공적으로 저장했습니다.";
        } else {
            return "데이터를 저장하지 못했습니다.";
        }
    }
}
