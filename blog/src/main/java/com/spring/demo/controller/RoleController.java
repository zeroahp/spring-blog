package com.spring.demo.controller;


import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/")
    ResponseEntity<ResponseData> addRole(@RequestBody RoleRequest roleRequest) {
        RoleDTO roleDTO = roleService.addRole(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseData.builder()
                        .data(roleDTO)
                        .desc("Role added successfully" + roleDTO.getRole_id())
                        .build());
    }

    @GetMapping("/")
    ResponseEntity<String> getRoles() {
        return  ResponseEntity.ok("ok");
    }


}
