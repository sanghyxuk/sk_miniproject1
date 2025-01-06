package com.backend.model.entity;

import com.backend.model.converter.JsonArrayConverter;
import jakarta.persistence.*;

import java.util.List;

// Questions 테이블과 매핑되는 엔터티 클래스
@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId; // 문제 ID

    private String phrase; // 사자성어
    private String description; // 설명
    private String correctAnswer; // 정답

    @Convert(converter = JsonArrayConverter.class)
    private List<String> choices; // 선택지 목록

    // Getters and Setters
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
