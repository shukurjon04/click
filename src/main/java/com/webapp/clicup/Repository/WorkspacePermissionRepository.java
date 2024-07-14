package com.webapp.clicup.Repository;

import com.webapp.clicup.Entity.WorkspacePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}