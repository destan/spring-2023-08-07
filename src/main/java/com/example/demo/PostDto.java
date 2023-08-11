package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PostDto {

    private String name;

    private String content;

    public PostDto(Object[] row, Map<String, Integer> aliasToIndexMap) {
        this.name = row[aliasToIndexMap.get("post_name")].toString();
        this.content = row[aliasToIndexMap.get("post_content")].toString();
    }
}
