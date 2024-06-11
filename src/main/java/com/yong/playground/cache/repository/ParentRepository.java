package com.yong.playground.cache.repository;

import com.yong.playground.cache.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findTopByOrderByCreateTimeDesc();
}
