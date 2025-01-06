package com.backend.service;

import com.backend.model.entity.Response;
import com.backend.repository.QuestionRepository;
import com.backend.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 사용자의 응답 데이터를 처리하는 서비스 계층
@Service
public class ResponseService {
    @Autowired
    private ResponseRepository responseRepository; // Responses 테이블과 상호작용하는 리포지토리

    @Autowired
    private QuestionRepository questionRepository; // Questions 테이블과 상호작용하는 리포지토리

    /**
     * 사용자의 응답 데이터를 저장하고 정답 여부를 확인합니다.
     *
     * @param response 사용자 응답 객체
     * @return 저장된 응답 객체
     */
    public Response saveResponse(Response response) {
        // 사용자가 응답한 문제의 정답을 데이터베이스에서 조회
        String correctAnswer = questionRepository.findById(response.getQuestionId())
                .orElseThrow(() -> new RuntimeException("해당 문제를 찾을 수 없습니다."))
                .getCorrectAnswer();

        // 사용자의 응답과 정답 비교 후 정답 여부 설정
        response.setIsCorrect(correctAnswer.equals(response.getUserAnswer()));

        // 응답 데이터를 데이터베이스에 저장
        return responseRepository.save(response);
    }

    /**
     * 특정 사용자의 모든 응답 데이터를 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 사용자 응답 목록
     */
    public List<Response> getResponsesByUserId(String userId) {
        return responseRepository.findByUserId(userId);
    }

    /**
     * 특정 문제에 대한 사용자의 응답을 조회합니다.
     *
     * @param userId 사용자 ID
     * @param questionId 문제 ID
     * @return 사용자 응답 객체
     */
    public Response getResponseByUserAndQuestion(String userId, Long questionId) {
        return responseRepository.findByUserIdAndQuestionId(userId, questionId)
                .orElseThrow(() -> new RuntimeException("해당 응답을 찾을 수 없습니다."));
    }
}
