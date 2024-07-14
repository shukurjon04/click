package com.webapp.clicup.Entity;

import com.webapp.clicup.Entity.Template.AbstractEntity;
import com.webapp.clicup.Entity.Template.AbstractMainBody;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceUser extends AbstractEntity{

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WorkSpace workSpace;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private user user;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private WorkspaceRole workspaceRole;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp dateInvited;

    @Column
    private Timestamp dateJoined;

}
