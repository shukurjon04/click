package com.webapp.clicup.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkspacePermissionName {
    MANAGE_USER("Manage Users","Gives the user permission to view and manage all members and guests in a Workspace." +
            " This includes adding and removing users, changing roles, and managing invites.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    USE_GIT("GIT","Allows the user to see and open the Github/Bitbucket/Gitlab modal on tasks and use all the features within it.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    EDIT_STATUSES("Edit Statuses","Gives the user the permission to create, edit, and delete statuses. " +
            "If you have Edit Statuses toggled on, but Delete Items off, you will not be able to delete statuses.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    MANAGE_TAGS("Manage Tags","Gives the user the permission to create, edit, and delete tags. " +
            "If you have Manage Tags toggled on, but Delete Items off, you will not be able to delete tags.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    SEND_EMAIL("Send Email (Email ClickApp)","Gives the user the permission to send email through the Email ClickApp.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    ADD_EMAIL_ACCOUNT("Add Email Accounts (Email ClickApp)","Gives the user the permission to add authorized email accounts through the Email ClickApp.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    MANAGE_CUSTOMS_FIELD("Manage Custom Fields","Gives the user the permission to create, edit, and delete Custom Fields. " +
            "If you have Manage Custom Fields toggled on, but Delete Items (below) off, you will not be able to delete Custom Fields.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    PINNED_CUSTOMS_FIELD("Pinned Custom Fields","Gives the user the ability to pin a Custom Field. " +
            "Pinned Custom Fields are pinned across the Workspace wherever that field is being used.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    CUSTOM_ROLES("Custom Roles","Gives the user the ability to create, edit, delete and manage all custom roles. " +
            "Users with this permission by default have access to all permissions. Only custom admins can be granted this permission.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_OWNER)),


    CREATE_SPACES("Create Spaces","Gives the user the permission to create Spaces in the team Workspace.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    CREATE_VIEWS("Create Views","Gives the user the permission to create and edit views on locations. " +
            "When toggled off, full Members can still create private views.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    DELETE_ITEMS("Delete Items","Gives the user the permission to delete items. " +
            "Optionally, you can have it so they can only delete tasks they create.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    EXPORTING("Exporting","Gives the user permission to export via the Workspace export setting.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    IMPORTING("Importing","Gives the user permission to import tasks via the Workspace import settings.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    INVITE_GUEST("Invite Guests","Gives the user the permission to invite guests",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    WORKSPACE_INTEGRATION("Workspace Integrations","Gives the user the permission to setup any third party Workspace integrations.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_MEMBER,WorkspaceRoleName.WORKSPACE_OWNER)),


    WORKSPACE_PERMISSiONS("Workspace Permissions","Gives the user the permission to change Workspace level permissions like (2FA, Public Sharing, SSO).",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_OWNER)),


    VIEW_TEAM_TIMESHEET("View Team Timesheet","Gives the user the permission to setup any third party Workspace integrations.",
            Arrays.asList(WorkspaceRoleName.WORKSPACE_ADMIN,WorkspaceRoleName.WORKSPACE_OWNER));


    private String name;
    private  String description;

    private  List<WorkspaceRoleName> workspaceRoleNames;

    WorkspacePermissionName(String name, String description, List<WorkspaceRoleName> workspaceRoleNames) {
        this.name = name;
        this.description = description;
        this.workspaceRoleNames = workspaceRoleNames;
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

    public List<WorkspaceRoleName> getWorkspaceRoleNames() {
        return workspaceRoleNames;
    }

    public void setWorkspaceRoleNames(List<WorkspaceRoleName> workspaceRoleNames) {
        this.workspaceRoleNames = workspaceRoleNames;
    }
}
