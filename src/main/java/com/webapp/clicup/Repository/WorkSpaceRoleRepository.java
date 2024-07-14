package com.webapp.clicup.Repository;

import com.webapp.clicup.Entity.WorkSpace;
import com.webapp.clicup.Entity.WorkspaceRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkSpaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
}