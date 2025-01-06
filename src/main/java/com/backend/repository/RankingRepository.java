package com.backend.repository;

import com.backend.model.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Rankings 테이블과 상호작용하는 리포지토리
public interface RankingRepository extends JpaRepository<Ranking, String> {
    // 총 점수 기준 상위 10명의 랭킹 조회
    List<Ranking> findTop10ByOrderByTotalScoreDesc();
}
