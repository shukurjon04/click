package com.webapp.clicup.Entity.Template;

import com.webapp.clicup.Entity.user;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
public class AbstractEntity extends AbstractMainBody{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


}
