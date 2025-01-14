package com.spring.demo.controller;


import com.spring.demo.model.dto.PermissionDTO;
import com.spring.demo.model.dto.RoleDTO;
import com.spring.demo.model.request.PermissionRequest;
import com.spring.demo.model.request.RoleRequest;
import com.spring.demo.model.response.ResponseData;
import com.spring.demo.service.PermissionService;
import com.spring.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermisstionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/")
    ResponseEntity<ResponseData> createPermission(@RequestBody PermissionRequest permissionRequest) {
        PermissionDTO permissionDTO = permissionService.createPermission(permissionRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseData.builder()
                        .data(permissionDTO)
                        .desc("Permission created successfully" + permissionDTO.getPermissionId())
                        .build());
    }

    @GetMapping("/")
    ResponseEntity<ResponseData> getAllPermissions() {

        return ResponseEntity.ok()
                .body(ResponseData.builder()
                        .data(permissionService.getAllPermissions())
                        .desc("All permissions found!" )
                        .build());
    }

}
