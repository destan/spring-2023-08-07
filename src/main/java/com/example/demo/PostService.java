package com.example.demo;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final TagRepository tagRepository;

    public Page<Post> listAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Post createPost(Post post) {
        Set<Tag> tags = post.getTags();
        post.setTags(null);
        postRepository.save(post);

        Set<Tag> persistedTags = tags.stream()
                .map(tag -> {
                    if (tag.getId() != null) {
                        return tag;
                    }

                    Optional<Tag> persistedTagOptional = tagRepository.findByName(tag.getName());
                    if (persistedTagOptional.isPresent()) {
                        return persistedTagOptional.get();
                    }
                    tagRepository.save(tag);
                    return tag;
                })
                .collect(Collectors.toSet());

        post.setTags(persistedTags);

        return post;
    }

    public Page<Post> search(@NotBlank String tagName, Pageable pageable) {
        return postRepository.findByTags_Name(tagName, pageable);
    }

    public Page<Post> search(String tagName, String searchTerm, Pageable pageable) {
        return postRepository.findByTags_NameAndContentLike(tagName, searchTerm, pageable);
    }
}
