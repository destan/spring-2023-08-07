package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostRepository postRepository;

    private final TagRepository tagRepository;

    @GetMapping
    ResponseEntity<Page<Post>> list(Pageable pageable) {

        Page<Post> posts = postRepository.findAll(pageable);

        return ResponseEntity.ok(posts);
    }

    @PostMapping
    ResponseEntity<Post> create(@RequestBody Post post) {

        Set<Tag> tags = post.getTags();

        List<Tag> transientTags = tags.stream().filter(tag -> tag.getId() == null).toList();

        //transientTags.forEach(tagRepository::save);

        tagRepository.saveAll(transientTags);

        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post)); //xxx is post.tags[0] detached or managed?
    }

}
