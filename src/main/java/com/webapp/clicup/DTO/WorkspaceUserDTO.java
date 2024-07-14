package com.webapp.clicup.DTO;

import com.webapp.clicup.enums.WorkspaceUserAddType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceUserDTO {

    @NotNull
    private UUID userId;
    @NotNull
    private UUID roleId;

    @NotNull
    private WorkspaceUserAddType workspaceUserAddType;

}
