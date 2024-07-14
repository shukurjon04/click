package com.webapp.clicup.Service;

import com.webapp.clicup.DTO.ApiResponse;
import com.webapp.clicup.DTO.WorkspaceDTO;
import com.webapp.clicup.DTO.WorkspaceUserDTO;
import com.webapp.clicup.Entity.*;
import com.webapp.clicup.Repository.*;
import com.webapp.clicup.enums.WorkspacePermissionName;
import com.webapp.clicup.enums.WorkspaceRoleName;
import com.webapp.clicup.enums.WorkspaceUserAddType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceService {


    private WorkspaceRepository workspaceRepository;
    private AttachmentRepository attachmentRepository;
    private final WorkSpaceRoleRepository workSpaceRoleRepository;
    private final WorkspaceUserRepository workspaceUserRepository;
    private final WorkspacePermissionRepository workspacePermissionRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository, AttachmentRepository attachmentRepository,
                            WorkSpaceRoleRepository workSpaceRoleRepository,
                            WorkspaceUserRepository workspaceUserRepository,
                            WorkspacePermissionRepository workspacePermissionRepository,
                            UserRepository userRepository) {
        this.workspaceRepository = workspaceRepository;
        this.attachmentRepository = attachmentRepository;
        this.workSpaceRoleRepository = workSpaceRoleRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse ADD(WorkspaceDTO workspaceDTO, user user) {
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName())) {
            return new ApiResponse("workspace name using already exists", false, 400, null);
        }
        WorkSpace workSpace = new WorkSpace();
        workSpace.setName(workspaceDTO.getName());
        workSpace.setColor((workspaceDTO.getColor().isEmpty() || workspaceDTO.getColor() == null) ? "blue" : workspaceDTO.getColor());
        workSpace.setOwner(user);
        workSpace.setAvatar((workspaceDTO.getAttachmentid() == null) ? null : attachmentRepository.findById(workspaceDTO.getAttachmentid()).orElseThrow(() -> new ResourceNotFoundException("Avatar not found")));
        WorkSpace save = workspaceRepository.save(workSpace);

        WorkspaceRole workspaceRoleOwner = workSpaceRoleRepository.save(
                new WorkspaceRole(
                        WorkspaceRoleName.WORKSPACE_OWNER.getName(),
                        save,
                        null)
        );
        WorkspaceRole workspaceRoleAdmin = workSpaceRoleRepository.save(
                new WorkspaceRole(
                        WorkspaceRoleName.WORKSPACE_ADMIN.getName(),
                        save,
                        null)
        );
        WorkspaceRole workspaceRoleMember = workSpaceRoleRepository.save(
                new WorkspaceRole(
                        WorkspaceRoleName.WORKSPACE_MEMBER.getName(),
                        save,
                        null)
        );
        WorkspaceRole workspaceRoleGuest = workSpaceRoleRepository.save(
                new WorkspaceRole(
                        WorkspaceRoleName.WORKSPACE_GUEST.getName(),
                        save,
                        null)
        );

        List<WorkspacePermission> workspacePermissions = new ArrayList<>();
        for (WorkspacePermissionName value : WorkspacePermissionName.values()) {
            if (value.getWorkspaceRoleNames().contains(WorkspaceRoleName.WORKSPACE_ADMIN))
                workspacePermissions.add(new WorkspacePermission(workspaceRoleAdmin, value));

            if (value.getWorkspaceRoleNames().contains(WorkspaceRoleName.WORKSPACE_OWNER))
                workspacePermissions.add(new WorkspacePermission(workspaceRoleOwner, value));

            if (value.getWorkspaceRoleNames().contains(WorkspaceRoleName.WORKSPACE_MEMBER))
                workspacePermissions.add(new WorkspacePermission(workspaceRoleMember, value));

            if (value.getWorkspaceRoleNames().contains(WorkspaceRoleName.WORKSPACE_GUEST))
                workspacePermissions.add(new WorkspacePermission(workspaceRoleGuest, value));
        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        workspaceUserRepository.save(new WorkspaceUser(
                save,
                user,
                workspaceRoleOwner,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("created", true, 200, save);


    }

    public ApiResponse EDIT(Long id, WorkspaceDTO workspaceDTO) {
        return null;
    }

    public ApiResponse DELTE(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("deleted", true, 200, null);
        } catch (Exception e) {
            return new ApiResponse("deleted error ", false, 400, null);
        }
    }

    public ApiResponse EDITOWNER(Long id, UUID userid) {
        return null;
    }

    public ApiResponse EditOrRemoveWorkspaceUser(Long id, WorkspaceUserDTO workspaceUserDTO) {
        if (workspaceUserDTO.getWorkspaceUserAddType().equals(WorkspaceUserAddType.ADD)) {

            WorkspaceUser save = workspaceUserRepository.save(new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace not found ")),
                    userRepository.findById(workspaceUserDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found")),
                    workSpaceRoleRepository.findById(workspaceUserDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role not found")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            ));
            return new ApiResponse("success", true, 200, save);
        } else if (workspaceUserDTO.getWorkspaceUserAddType().equals(WorkspaceUserAddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByUserIdAndWorkSpaceId(workspaceUserDTO.getUserId(), id).orElseThrow(() -> new ResourceNotFoundException("not found"));
            workspaceUser.setWorkspaceRole(workSpaceRoleRepository.findById(workspaceUserDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role not found")));

            WorkspaceUser save = workspaceUserRepository.save(workspaceUser);

            return new ApiResponse("success", true, 200, save);


        } else if (workspaceUserDTO.getWorkspaceUserAddType().equals(WorkspaceUserAddType.DELETE)) {
            workspaceUserRepository.deleteByUserIdAndWorkSpaceId(workspaceUserDTO.getUserId(), id);
        }
        return new ApiResponse("unsupported type", false, 400, null);
    }

    public ApiResponse JOINMEMBER(Long id, user user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserIdAndWorkSpaceId(user.getId(), id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
        WorkspaceUser save = workspaceUserRepository.save(workspaceUser);
        return new ApiResponse("joined",true,200,null);

    }
}
