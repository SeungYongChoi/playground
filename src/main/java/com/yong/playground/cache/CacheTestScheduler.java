package com.yong.playground.cache;

import com.yong.playground.cache.domain.Child;
import com.yong.playground.cache.domain.Parent;
import com.yong.playground.cache.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheTestScheduler {
    private final ParentRepository parentRepository;
    private int index;

    @Scheduled(fixedDelay = 1000L)
    public void saveParentWithChildren() {
        log.info("Scheduling~");
        Optional<Parent> parentOptional = parentRepository.findTopByOrderByCreateTimeDesc();
        if (parentOptional.isEmpty()) {
            Parent parent = Parent.builder()
                    .name("parent")
                    .createTime(LocalDateTime.now())
                    .build();

            parentRepository.save(parent);
            return;
        }

        Parent parent = parentOptional.get();
        parent.addChild(
                Child.builder()
                        .name("child" + index)
                        .parent(parent)
                        .build()
        );

        parentRepository.save(parent);
    }
}
