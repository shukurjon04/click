package com.webapp.clicup.Controller;

import com.webapp.clicup.DTO.ApiResponse;
import com.webapp.clicup.DTO.RegisterUserDTO;
import com.webapp.clicup.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userDTO){
        ApiResponse register = authService.register(userDTO);
        return ResponseEntity.status(register.getCode()).body(register);
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email,@RequestParam String code){
        ApiResponse register = authService.verificationUser(email, code);
        return ResponseEntity.status(register.getCode()).body(register);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email , @RequestParam String password){
        ApiResponse register = authService.login(email,password);
        return ResponseEntity.status(register.getCode()).body(register);
    }

}
