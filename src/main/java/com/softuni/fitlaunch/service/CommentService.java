package com.softuni.fitlaunch.service;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.view.CommentView;
import com.softuni.fitlaunch.model.entity.CommentEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.repository.CommentRepository;
import com.softuni.fitlaunch.repository.UserRepository;
import com.softuni.fitlaunch.repository.WorkoutRepository;
import com.softuni.fitlaunch.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final WorkoutRepository workoutRepository;
    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, WorkoutRepository workoutRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.workoutRepository = workoutRepository;
        this.modelMapper = modelMapper;
    }

    public List<CommentView> getAllCommentsForWorkout(Long workoutId) {
        WorkoutEntity workout = workoutRepository.findById(workoutId).orElseThrow(() -> new ObjectNotFoundException("Workout not found"));

        List<CommentEntity> comments = commentRepository.findAllByWorkout(workout).get();

        return comments.stream().map(commentEntity -> new CommentView(commentEntity.getId(), commentEntity.getAuthor().getUsername(), commentEntity.getContent())).collect(Collectors.toList());
    }

    public CommentEntity addComment(CommentCreationDTO commentDTO, Long workoutId, UserEntity author) {

        CommentEntity comment = new CommentEntity();
        comment.setWorkout(workoutRepository.findById(workoutId).get());
        comment.setAuthor(author);
        comment.setContent(commentDTO.getMessage());
        commentRepository.save(comment);

        return comment;
    }

    public CommentEntity getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }


    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

}
