package com.softuni.fitlaunch.service;

import com.softuni.fitlaunch.model.dto.comment.CommentDTO;
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

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, WorkoutRepository workoutRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public CommentView addComment(CommentDTO commentDTO) {
        UserEntity author = userRepository.findByUsername(commentDTO.getAuthorName()).get();

        CommentEntity comment = modelMapper.map(commentDTO, CommentEntity.class);
        commentRepository.save(comment);

        return new CommentView(comment.getId(), author.getUsername(), comment.getContent());
    }

    public List<CommentDTO> getAllCommentsForWorkout(Long workoutId) {
        WorkoutEntity workout = workoutRepository.findById(workoutId).orElseThrow(() -> new ObjectNotFoundException("Workout not found"));

        List<CommentEntity> comments = commentRepository.findAllByWorkout(workout).get();

        return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(commentEntity -> modelMapper.map(commentEntity, CommentDTO.class))
                .toList();
    }
}
