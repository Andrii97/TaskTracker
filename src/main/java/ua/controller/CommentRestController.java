package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.controller.dto.CommentDTO;
import ua.model.entity.Comment;
import ua.model.repository.CommentRepository;
import ua.model.repository.TaskRepository;
import ua.model.repository.UserRepository;

@RepositoryRestController
public class CommentRestController {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = getCommentFromDTO(commentDTO);
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    @PutMapping(path = "/comments/{id}")//, value = "/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        if (commentRepository.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Comment comment = getCommentFromDTO(commentDTO);
        comment.setId(id);
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    private Comment getCommentFromDTO(CommentDTO commentDTO) {
        System.out.println(commentDTO.toString());
        Comment comment = new Comment();
        comment.setBody(commentDTO.getBody());
        comment.setSender(userRepository.findOne(commentDTO.getSenderId()));
        comment.setTask(taskRepository.findOne(commentDTO.getTaskId()));
        return comment;
    }

}
