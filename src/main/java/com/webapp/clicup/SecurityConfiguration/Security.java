package com.webapp.clicup.SecurityConfiguration;

import com.webapp.clicup.JWTTOKEN.JwtFilter;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class Security {
    @Autowired
    JwtFilter filter;


    private static final String[] OPEN_PASSAGE ={
            "/api/auth/**",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csr -> csr.disable())
                .formLogin(t -> t.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth.requestMatchers(OPEN_PASSAGE).permitAll().anyRequest().authenticated());
        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //email xabar yuborish

    @Value("${spring.mail.host}")
    private String SpringMailHost;
    @Value("${spring.mail.port}")
    private Integer SpringMailPort;
    @Value("${spring.mail.username}")
    private String SpringMailUserName;
    @Value("${spring.mail.password}")
    private String SpringMailPassword;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean SpringMailPropertiesSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls}")
    private boolean SpringMailPropertiesSmtpStarttlsEnable;
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(this.SpringMailHost);
        sender.setPort(this.SpringMailPort);

        sender.setUsername(this.SpringMailUserName);
        sender.setPassword(this.SpringMailPassword);

        Properties properties = sender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth", this.SpringMailPropertiesSmtpAuth);
        properties.put("mail.smtp.starttls.enable", this.SpringMailPropertiesSmtpStarttlsEnable);
        properties.put("mail.debug", "true");

        return sender;
    }


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
