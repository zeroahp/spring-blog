package com.spring.demo.repository;

import com.spring.demo.model.entity.PostEntity;
import com.spring.demo.model.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,String > {
    Optional<RoleEntity> findByRoleId(Long roleId);
    Optional<RoleEntity> findByRoleName(String roleName);
}
