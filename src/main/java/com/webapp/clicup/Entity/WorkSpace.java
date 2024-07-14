package com.webapp.clicup.Entity;

import com.webapp.clicup.Entity.Template.AbstractLongEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name","Owner_id"}))
public class WorkSpace extends AbstractLongEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne
    private user Owner;

    @Column(nullable = false)
    private String initletter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment Avatar;


    @PrePersist
    @PreUpdate
    public void Initlattername(){
        this.setInitletter(getName().substring(0,1));
    }
}
