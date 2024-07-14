package com.webapp.clicup.Controller;

import com.webapp.clicup.AOP.CurrentUser;
import com.webapp.clicup.DTO.ApiResponse;
import com.webapp.clicup.DTO.WorkspaceDTO;
import com.webapp.clicup.DTO.WorkspaceUserDTO;
import com.webapp.clicup.Entity.WorkSpace;
import com.webapp.clicup.Entity.user;
import com.webapp.clicup.Service.WorkspaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody WorkspaceDTO workspaceDTO, @CurrentUser user user) {
        ApiResponse addspace = workspaceService.ADD(workspaceDTO,user);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }

    @PostMapping("/WorkspaceUser/{id}")
    public ResponseEntity<?> addOrEditOrRemoveWorkspaceUser(@PathVariable Long id ,@Valid @RequestBody WorkspaceUserDTO workspaceUserDTO) {
        ApiResponse addspace = workspaceService.EditOrRemoveWorkspaceUser(id,workspaceUserDTO);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id ,@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        ApiResponse addspace = workspaceService.EDIT(id,workspaceDTO);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }

    @PutMapping("/changeOwner/{id}")
    public ResponseEntity<?> editOwner(@PathVariable Long id , @RequestParam UUID userid) {
        ApiResponse addspace = workspaceService.EDITOWNER(id,userid);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse addspace = workspaceService.DELTE(id);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }

    @PutMapping("/join")
    public ResponseEntity<?> joinuser(@RequestParam Long id , @CurrentUser user user) {
        ApiResponse addspace = workspaceService.JOINMEMBER(id,user);
        return ResponseEntity.status(addspace.getCode()).body(addspace);
    }
}
