package com.webapp.clicup.Entity.Template;

import com.webapp.clicup.Entity.user;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractMainBody {

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp CreatedAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp UpdateAt;

    @JoinColumn(updatable = false)
    @ManyToOne
    @CreatedBy
    private user createdBy;


    @ManyToOne
    @LastModifiedBy
    private user updatedBy;
}
