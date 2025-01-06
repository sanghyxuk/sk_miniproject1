package com.backend.service;

import com.backend.model.entity.Question;
import com.backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 랜덤 문제 데이터를 제공하는 서비스
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getRandomQuestions(int limit) {
        return questionRepository.findRandomQuestions(limit);
    }
}
