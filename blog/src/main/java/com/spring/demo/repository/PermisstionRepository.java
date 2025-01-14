package com.spring.demo.repository;

import com.spring.demo.model.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PermisstionRepository extends JpaRepository<PermissionEntity,String > {
    List<PermissionEntity> findByPermissionNameIn(Collection<String> permissionName);

}
