package com.webapp.clicup.Repository;

import com.webapp.clicup.Entity.WorkspaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {

    Optional<WorkspaceUser> findByUserIdAndWorkSpaceId(UUID user_id, Long workSpace_id);

    @Transactional
    @Modifying
    void deleteByUserIdAndWorkSpaceId(UUID user_id, Long workSpace_id);
}