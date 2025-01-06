package com.backend.controller;

import com.backend.model.entity.Response;
import com.backend.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 사용자의 응답 데이터를 관리하는 컨트롤러 계층
@RestController
@RequestMapping("/api")
public class ResponseController {
    @Autowired
    private ResponseService responseService; // 응답 데이터를 처리하는 서비스

    /**
     * 사용자의 응답 데이터를 저장합니다.
     *
     * @param response 사용자 응답 객체
     * @return 저장된 응답 객체
     */
    @PostMapping("/responses")
    public ResponseEntity<Response> saveResponse(@RequestBody Response response) {
        Response savedResponse = responseService.saveResponse(response);
        return ResponseEntity.ok(savedResponse);
    }

    /**
     * 특정 사용자의 모든 응답 데이터를 조회합니다.
     *
     * @param userId 사용자 ID
     * @return 사용자 응답 목록
     */
    @GetMapping("/responses/{userId}")
    public ResponseEntity<List<Response>> getResponsesByUserId(@PathVariable String userId) {
        List<Response> responses = responseService.getResponsesByUserId(userId);
        return ResponseEntity.ok(responses);
    }

    /**
     * 특정 문제에 대한 사용자의 응답을 조회합니다.
     *
     * @param userId 사용자 ID
     * @param questionId 문제 ID
     * @return 사용자 응답 객체
     */
    @GetMapping("/responses/{userId}/{questionId}")
    public ResponseEntity<Response> getResponseByUserAndQuestion(
            @PathVariable String userId, @PathVariable Long questionId) {
        Response response = responseService.getResponseByUserAndQuestion(userId, questionId);
        return ResponseEntity.ok(response);
    }
}
