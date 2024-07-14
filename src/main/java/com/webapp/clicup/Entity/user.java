package com.webapp.clicup.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webapp.clicup.Entity.Template.AbstractEntity;
import com.webapp.clicup.enums.SystemRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Users")
public class user extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private String FullName;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initLetter;

    @Column(nullable = false)
    private boolean isEnabled = false;

    @JsonIgnore
    private String VerificationCode;

    @Enumerated(EnumType.STRING)
    private SystemRole systemRole;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment Avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(getSystemRole().getName());
        return List.of(authority);
    }

    public user(String fullName, String email, String password, String color, String verificationCode, SystemRole systemRole) {
        FullName = fullName;
        this.email = email;
        this.password = password;
        this.color = color;
        VerificationCode = verificationCode;
        this.systemRole = systemRole;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @PrePersist
    @PreUpdate
    public void inintgtri(){
        this.setInitLetter(this.getFullName().substring(0,1));
    }
}
