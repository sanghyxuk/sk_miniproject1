package com.backend.controller;

import com.backend.entity.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Order.desc("score"), Sort.Order.asc("createdAt")));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/{nickname}")
    public Optional<User> getUserByNickname(@PathVariable String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @GetMapping("/surround/{nickname}")
    public List<User> getSurroundingUsers(@PathVariable String nickname) {
        List<User> sortedUsers = userRepository.findAll(Sort.by(Sort.Order.desc("score"), Sort.Order.asc("createdAt")));
        int index = -1;

        for (int i = 0; i < sortedUsers.size(); i++) {
            if (sortedUsers.get(i).getNickname().equals(nickname)) {
                index = i;
                break;
            }
        }

        if (index == -1) return Collections.emptyList();

        int start = Math.max(0, index - 1);
        int end = Math.min(sortedUsers.size(), index + 2);
        return sortedUsers.subList(start, end);
    }
}
