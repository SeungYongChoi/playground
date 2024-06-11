package com.yong.playground.cache.repository;

import com.yong.playground.cache.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
