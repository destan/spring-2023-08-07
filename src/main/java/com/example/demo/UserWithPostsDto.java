package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserWithPostsDto {

    private String username;

    private List<PostDto> posts;

    public UserWithPostsDto(Object[] row, Map<String, Integer> aliasToIndexMap) {
        this.username = row[aliasToIndexMap.get("username")].toString();
        this.posts = new ArrayList<>();
    }
}
