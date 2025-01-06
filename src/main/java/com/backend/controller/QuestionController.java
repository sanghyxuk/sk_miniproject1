package com.backend.controller;

import com.backend.model.entity.Question;
import com.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// API 요청을 처리하고 랜덤 퀴즈 데이터를 반환하는 컨트롤러
@RestController
@RequestMapping("/api")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    // 랜덤으로 지정된 개수의 퀴즈를 가져오는 API
    @GetMapping("/questions")
    public List<Question> getRandomQuestions(@RequestParam int limit) {
        return questionService.getRandomQuestions(limit);
    }
}
