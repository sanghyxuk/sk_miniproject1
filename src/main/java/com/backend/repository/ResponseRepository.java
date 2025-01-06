package com.backend.repository;

import com.backend.model.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Responses 테이블과 상호작용하는 리포지토리 인터페이스
public interface ResponseRepository extends JpaRepository<Response, Long> {
    /**
     * 특정 사용자의 모든 응답 데이터를 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 사용자 응답 목록
     */
    List<Response> findByUserId(String userId);

    /**
     * 특정 문제에 대한 사용자의 응답을 조회합니다.
     *
     * @param userId 사용자 ID
     * @param questionId 문제 ID
     * @return 사용자 응답 객체 (Optional)
     */
    Optional<Response> findByUserIdAndQuestionId(String userId, Long questionId);
}
