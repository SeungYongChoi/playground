package com.yong.playground.cache;

import com.yong.playground.cache.dto.CacheTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CacheTestController {

    private final CacheTestService cacheTestService;

    @GetMapping()
    public ResponseEntity<CacheTestResponse> cacheTest() {
        CacheTestResponse cacheTestResponse = cacheTestService.cacheTest();
        return ResponseEntity.ok(cacheTestResponse);
    }
}
