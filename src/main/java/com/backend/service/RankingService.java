package com.backend.service;

import com.backend.model.entity.Ranking;
import com.backend.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 사용자 점수를 관리하고 랭킹을 조회하는 서비스 클래스
@Service
public class RankingService {
    @Autowired
    private RankingRepository rankingRepository;

    /**
     * 사용자 점수를 업데이트합니다.
     * - 사용자 ID가 존재하지 않으면 새로 생성
     * - 사용자 ID가 존재하면 점수를 업데이트
     *
     * @param userId 사용자 ID
     * @param score 추가할 점수
     */
    public void updateScore(String userId, int score) {
        Ranking ranking = rankingRepository.findById(userId)
                .orElse(new Ranking(userId, 0)); // 사용자 ID가 없으면 초기화
        ranking.setTotalScore(ranking.getTotalScore() + score); // 점수 추가
        rankingRepository.save(ranking);
    }

    /**
     * 상위 10명의 사용자 랭킹을 조회합니다.
     *
     * @return 상위 10명의 랭킹 목록
     */
    public List<Ranking> getTopRankings() {
        return rankingRepository.findTop10ByOrderByTotalScoreDesc();
    }
}
