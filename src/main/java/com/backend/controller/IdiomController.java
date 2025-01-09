package com.backend.controller;

import com.backend.entity.Idiom;
import com.backend.repository.IdiomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/idioms")
public class IdiomController {

    @Autowired
    private IdiomRepository idiomRepository;

    // 반환된 ID 추적 (중복 방지)
    private Set<Long> usedIds = new HashSet<>();

    @GetMapping("/random")
    public Optional<Idiom> getRandomIdiom() {
        // 모든 사자성어 가져오기
        List<Idiom> idioms = idiomRepository.findAll();
        if (idioms.isEmpty()) {
            return Optional.empty();
        }

        // 사용되지 않은 사자성어 필터링
        List<Idiom> availableIdioms = new ArrayList<>();
        for (Idiom idiom : idioms) {
            if (!usedIds.contains(idiom.getId())) {
                availableIdioms.add(idiom);
            }
        }

        // 모든 데이터가 사용되었다면 초기화
        if (availableIdioms.isEmpty()) {
            usedIds.clear(); // 추적 초기화
            availableIdioms.addAll(idioms);
        }

        // 랜덤으로 사자성어 선택
        int randomIndex = (int) (Math.random() * availableIdioms.size());
        Idiom selectedIdiom = availableIdioms.get(randomIndex);

        // 선택된 ID를 사용된 목록에 추가
        usedIds.add(selectedIdiom.getId());

        return Optional.of(selectedIdiom);
    }
}
