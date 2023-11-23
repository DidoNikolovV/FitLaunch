package com.softuni.fitlaunch.web.rest;


import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.comment.CommentMessageDTO;
import com.softuni.fitlaunch.model.dto.view.CommentView;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.service.CommentService;
import com.softuni.fitlaunch.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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

    @GetMapping("/{workoutId}/comments")
    public ResponseEntity<List<CommentView>> getCommentsByWorkoutId(@PathVariable("workoutId") Long workoutId) {
        return ResponseEntity.ok(commentService.getAllCommentsForWorkout(workoutId));
    }

    @PostMapping(value = "/{workoutId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentView> postComment(@PathVariable("workoutId") Long workoutId,
                                                   @RequestBody CommentMessageDTO commentMessageDTO,
                                                   Principal principal) {

        UserEntity user = userService.getUserByUsername(principal.getName());

        CommentCreationDTO commentDTO = new CommentCreationDTO(
                user.getUsername(),
                commentMessageDTO.getMessage()
        );


        CommentView comment = commentService.addComment(commentDTO);

        return ResponseEntity.created(
                URI.create(String.format(("/api/%d/comments/%d"), workoutId, comment.getId()))
        ).body(comment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CommentCreationDTO> deleteCommentById(@PathVariable("id") Long id) {

        commentService.deleteCommentById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}
