package com.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

// Rankings 테이블과 매핑되는 엔터티 클래스
@Entity
@Table(name = "Rankings")
public class Ranking {
    @Id
    private String userId; // 사용자 ID (Primary Key)

    private Integer totalScore; // 사용자 총 점수

    @UpdateTimestamp // 점수 갱신 시간
    private LocalDateTime lastUpdated;

    // 기본 생성자
    public Ranking() {}

    // 사용자 ID와 초기 점수를 받는 생성자
    public Ranking(String userId, Integer totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
