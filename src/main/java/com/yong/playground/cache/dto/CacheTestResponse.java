package com.yong.playground.cache.dto;

import com.yong.playground.cache.domain.Child;
import com.yong.playground.cache.domain.Parent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheTestResponse {
    private String parentName;
    private List<String> childrenNames;

    public static CacheTestResponse from(Parent parent) {
        return CacheTestResponse.builder()
                .parentName(parent.getName())
                .childrenNames(parent.getChildren().stream().map(Child::getName).collect(Collectors.toList()))
                .build();
    }

}
