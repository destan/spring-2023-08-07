package com.example.demo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("posts")
class PostController {

    private final PostService postService;

    @GetMapping
    ResponseEntity<Page<Post>> list(@RequestParam("tag") Optional<String> tagNameOptional, Pageable pageable) {

        if (tagNameOptional.isPresent()) {
            String tagName = tagNameOptional.get();
            Page<Post> result = postService.search(tagName, pageable);
            return ResponseEntity.ok(result);
        }

        Page<Post> posts = postService.listAll(pageable);

        return ResponseEntity.ok(posts);
    }

    @GetMapping("search")
    ResponseEntity<Page<Post>> search(Pageable pageable, @RequestParam("tag") String tagName, @RequestParam("searchTerm") String searchTerm) {

        Page<Post> posts = postService.search(tagName, searchTerm, pageable);

        return ResponseEntity.ok(posts);
    }

    @PostMapping
    ResponseEntity<Post> create(@RequestBody Post post) {

        Post persistedPost = postService.createPost(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(persistedPost); // xxx is post.tags[0] detached or managed?
    }

}
