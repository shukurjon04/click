package com.webapp.clicup.enums;


public enum WorkspaceRoleName {

    WORKSPACE_ADMIN("admin","admin"),
    WORKSPACE_OWNER("owner","owner"),
    WORKSPACE_MEMBER("member","member"),
    WORKSPACE_GUEST("guest","guest");


    private String name;
    private String description;

    WorkspaceRoleName(String name, String description) {
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
