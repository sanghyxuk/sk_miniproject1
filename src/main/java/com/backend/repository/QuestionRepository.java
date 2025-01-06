package com.backend.repository;

import com.backend.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Questions 테이블과 상호작용하는 리포지토리
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM Questions ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("limit") int limit);
}
