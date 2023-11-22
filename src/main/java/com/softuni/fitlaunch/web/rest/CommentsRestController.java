package com.softuni.fitlaunch.web.rest;


import com.softuni.fitlaunch.model.dto.comment.CommentDTO;
import com.softuni.fitlaunch.model.dto.comment.CommentMessageDTO;
import com.softuni.fitlaunch.model.dto.view.CommentView;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.service.CommentService;
import com.softuni.fitlaunch.service.CustomUserDetails;
import com.softuni.fitlaunch.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CommentsRestController {

    private final CommentService commentService;

    private final UserService userService;

    public CommentsRestController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<CommentDTO>> geAllComments() {
//        return ResponseEntity.ok(commentService.getAllComments());
//    }

    @PostMapping(value = "/{workoutId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentView> postComment(@PathVariable("workoutId") Long workoutId, @RequestBody CommentMessageDTO commentMessageDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        UserEntity user = userService.getUserById(userId);

        CommentDTO commentDTO = new CommentDTO(
                user.getUsername(),
                workoutId,
                commentMessageDTO.getMessage()
        );

        CommentView comment = commentService.addComment(commentDTO);

        return ResponseEntity.created(
                URI.create(String.format(("/api/%d/comments/%d"), workoutId, comment.getId()))
        ).body(comment);
    }

    @GetMapping("/{workoutId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByWorkoutId(@PathVariable("workoutId") Long workoutId) {
        return ResponseEntity.ok(commentService.getAllCommentsForWorkout(workoutId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> deleteCommentById(@PathVariable("id") Long id) {

        commentService.deleteCommentById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
