package com.softuni.fitlaunch.service;

import com.softuni.fitlaunch.model.dto.comment.CommentCreationDTO;
import com.softuni.fitlaunch.model.dto.view.CommentView;
import com.softuni.fitlaunch.model.entity.CommentEntity;
import com.softuni.fitlaunch.model.entity.UserEntity;
import com.softuni.fitlaunch.model.entity.WorkoutEntity;
import com.softuni.fitlaunch.repository.CommentRepository;
import com.softuni.fitlaunch.repository.ProgramWeekWorkoutRepository;
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
    private final ProgramWeekWorkoutRepository programWeekWorkoutRepository;

    public CommentService(CommentRepository commentRepository, WorkoutRepository workoutRepository, ModelMapper modelMapper, ProgramWeekWorkoutRepository programWeekWorkoutRepository) {
        this.commentRepository = commentRepository;
        this.workoutRepository = workoutRepository;
        this.modelMapper = modelMapper;
        this.programWeekWorkoutRepository = programWeekWorkoutRepository;
    }

    public List<CommentView> getAllCommentsForWorkout(Long workoutId) {
        WorkoutEntity workout = workoutRepository.findById(workoutId).orElseThrow(() -> new ObjectNotFoundException("Workout not found"));

        List<CommentEntity> comments = commentRepository.findAllByWorkout(workout).get();

        return comments.stream().map(commentEntity -> new CommentView(commentEntity.getId(), commentEntity.getAuthor().getUsername(), commentEntity.getContent())).collect(Collectors.toList());

    }

    public List<CommentView> getAllCommentsForWorkout(Long programId, Long weekId, Long workoutId) {
//        WorkoutEntity workout = workoutRepository.findById(workoutId).orElseThrow(() -> new ObjectNotFoundException("Workout not found"));

        List<CommentEntity> comments = commentRepository.findByProgramIdAndWeekIdAndWorkoutId(programId, weekId, workoutId).orElseThrow(() -> new ObjectNotFoundException("Comments not found"));
        List<CommentView> commentsDTO = comments.stream().map(commentEntity -> new CommentView(commentEntity.getId(), commentEntity.getAuthor().getUsername(), commentEntity.getContent())).collect(Collectors.toList());

        return commentsDTO;
    }


    public CommentEntity addComment(CommentCreationDTO commentDTO, Long workoutId, UserEntity author) {

        CommentEntity comment = new CommentEntity();
        comment.setWorkout(programWeekWorkoutRepository.findById(workoutId).get());
        comment.setAuthor(author);
        comment.setContent(commentDTO.getContent());
        commentRepository.save(comment);

        return comment;
    }

    public CommentEntity getComment(Long id) {
        return commentRepository.findById(id).orElse(null);
    }


    public CommentEntity deleteCommentById(Long id) {
        CommentEntity comment = this.getComment(id);
        commentRepository.deleteById(comment.getId());

        return comment;
    }

}
