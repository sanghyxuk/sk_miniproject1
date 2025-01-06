package com.backend.controller;

import com.backend.model.entity.Ranking;
import com.backend.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 사용자 점수 갱신 및 랭킹을 관리하는 컨트롤러
@RestController
@RequestMapping("/api")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    // 점수 갱신 API
    @PostMapping("/rankings/update")
    public ResponseEntity<?> updateScore(@RequestParam String userId, @RequestParam int score) {
        rankingService.updateScore(userId, score);
        return ResponseEntity.ok("Score updated successfully!");
    }

    // 상위 10명의 랭킹 조회 API
    @GetMapping("/rankings")
    public List<Ranking> getTopRankings() {
        return rankingService.getTopRankings();
    }
}
