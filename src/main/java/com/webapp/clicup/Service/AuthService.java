package com.webapp.clicup.Service;

import com.webapp.clicup.DTO.ApiResponse;
import com.webapp.clicup.DTO.RegisterUserDTO;
import com.webapp.clicup.Entity.user;
import com.webapp.clicup.JWTTOKEN.JwtFilter;
import com.webapp.clicup.JWTTOKEN.JwtProvider;
import com.webapp.clicup.Repository.UserRepository;
import com.webapp.clicup.Utils.generateCodeEmail;
import com.webapp.clicup.enums.SystemRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import javax.security.auth.login.LoginException;
import java.rmi.ServerError;

@Service
public class AuthService implements UserDetailsService {
    @Value("${spring.mail.username}")
    private String SpringMailUserName;

    @Autowired
    UserRepository userRepository;
    @Lazy
    @Autowired
    JwtFilter filter;
    @Lazy
    @Autowired
    JwtProvider jwtProvider;
    @Lazy
    @Autowired
    JavaMailSender javaMailSender;
    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;
    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse register(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            return new ApiResponse("this email already used", false, 403, null);
        }
        String code = generateCodeEmail.CodeGenerate(5);
        sendEmail(registerUserDTO.getEmail(), code);
        user user = new user();
        user.setFullName(registerUserDTO.getFullName());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setColor("Green");
        user.setVerificationCode(code);
        user.setSystemRole(SystemRole.SYSTEM_USER);
        user.setInitLetter(registerUserDTO.getFullName().substring(0, 1));

        user save = userRepository.save(user);
        return new ApiResponse("user saved successful", true, 200, save);
    }

    public ApiResponse verificationUser(String email, String Code) {
        user user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found this email"));
        if (user.getVerificationCode().equals(Code)) {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return new ApiResponse("verified", true, 200, jwtProvider.generateToken(user.getEmail()));
        }
        return new ApiResponse("not verified", false, 400, null);
    }

    public ApiResponse resendCode(String email) {
        user user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found this email"));
        String code = generateCodeEmail.CodeGenerate(5);
        sendEmail(email,code);
        user.setVerificationCode(code);
        userRepository.save(user);
        return new ApiResponse("verified", true, 200, jwtProvider.generateToken(user.getEmail()));
    }

    public ApiResponse login(String email , String password) {
        try {
            user user = (com.webapp.clicup.Entity.user) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password)).getPrincipal();
           return new ApiResponse("succes",true,200,jwtProvider.generateToken(user.getEmail()));
        }catch (Exception e){
            return new ApiResponse(e.getMessage(),false,400,null);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found or error"));
    }

    private boolean sendEmail(String sendEmail, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(message, true);
            mimeMessageHelper.setSubject("email verification by Shukurjon");
            mimeMessageHelper.setFrom(SpringMailUserName);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            throw new ServerErrorException("do not send message th given email", e.getCause());
        }
    }

}
