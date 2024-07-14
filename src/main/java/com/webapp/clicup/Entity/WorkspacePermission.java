package com.webapp.clicup.Entity;

import com.webapp.clicup.Entity.Template.AbstractEntity;
import com.webapp.clicup.enums.WorkspacePermissionName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspacePermission extends AbstractEntity {


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WorkspaceRole workspaceRole;

    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName permissionName;
}
