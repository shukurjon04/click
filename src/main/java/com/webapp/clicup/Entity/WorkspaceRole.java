package com.webapp.clicup.Entity;

import com.webapp.clicup.Entity.Template.AbstractEntity;
import com.webapp.clicup.Entity.Template.AbstractLongEntity;
import com.webapp.clicup.enums.WorkspaceRoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceRole extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WorkSpace workSpace;

    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName workspaceRole;
}
