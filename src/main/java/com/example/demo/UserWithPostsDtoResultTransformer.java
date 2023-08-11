package com.example.demo;

import org.hibernate.transform.ResultTransformer;

import java.util.*;

// We can safely ignore deprecated warning because: https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232
public class UserWithPostsDtoResultTransformer implements ResultTransformer<UserWithPostsDto> {

    private final Map<Long, UserWithPostsDto> userDtoCache = new LinkedHashMap<>();

    @Override
    public UserWithPostsDto transformTuple(Object[] row, String[] aliases) {
        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Long userId = Long.parseLong(row[0].toString());

        UserWithPostsDto userDto = userDtoCache.computeIfAbsent(userId, id -> new UserWithPostsDto(row, aliasToIndexMap));

        userDto.getPosts().add(new PostDto(row, aliasToIndexMap));

        return userDto;
    }

    @Override
    public List<UserWithPostsDto> transformList(List collection) {
        return new ArrayList<>(userDtoCache.values());
    }

    private Map<String, Integer> aliasToIndexMap(String[] aliases) {
        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(
                    aliases[i].toLowerCase(Locale.ROOT),
                    i
            );
        }

        return aliasToIndexMap;
    }
}
