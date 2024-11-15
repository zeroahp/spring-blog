package com.spring.demo.service;

import com.spring.demo.model.dto.CommentDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.CommentRequest;
import com.spring.demo.model.request.RoleRequest;

public interface CommentService {

    CommentDTO addComment(CommentRequest commentRequest);

}
