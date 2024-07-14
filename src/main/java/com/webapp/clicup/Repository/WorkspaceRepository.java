package com.webapp.clicup.Repository;

import com.webapp.clicup.Entity.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkSpace,Long> {
    boolean existsByOwnerIdAndName(UUID uuid,String name);
}
