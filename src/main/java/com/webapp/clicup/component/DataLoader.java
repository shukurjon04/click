package com.webapp.clicup.component;

import com.webapp.clicup.Entity.WorkspaceRole;
import com.webapp.clicup.Entity.user;
import com.webapp.clicup.Repository.UserRepository;
import com.webapp.clicup.Repository.WorkSpaceRoleRepository;
import com.webapp.clicup.enums.SystemRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String sqlInit;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkSpaceRoleRepository workSpaceRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (sqlInit.equals("always")) {

            userRepository.save(
                    new user(
                            "adminjon",
                            "admin@gmail.com",
                            passwordEncoder.encode("admin"),
                            "green",
                            null,
                            SystemRole.SYSTEM_ADMIN
                    )
            );


        }
    }
}
