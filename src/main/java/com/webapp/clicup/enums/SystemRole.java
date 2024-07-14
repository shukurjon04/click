package com.webapp.clicup.enums;

import lombok.Getter;
import lombok.Setter;


public enum SystemRole {

    SYSTEM_ADMIN("admin","admin"),
    SYSTEM_MODERATOR("moderator","moderator"),
    SYSTEM_USER("user","user");


    private String name;
    private String description;

    SystemRole(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
