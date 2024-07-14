package com.webapp.clicup.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDTO {

    @NotBlank(message = "workspace name cannot be empty")
    private String name;

    private String color;

    private UUID Attachmentid;
}
