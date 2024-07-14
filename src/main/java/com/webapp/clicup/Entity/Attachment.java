package com.webapp.clicup.Entity;

import com.webapp.clicup.Entity.Template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends AbstractEntity {

    @Column(nullable = false,updatable = false)
    private String name;

    @Column(nullable = false,updatable = false)
    private String OriginalName;

    @Column(nullable = false,updatable = false)
    private Long size;

    @Column(nullable = false,updatable = false)
    private String ContentType;


}
