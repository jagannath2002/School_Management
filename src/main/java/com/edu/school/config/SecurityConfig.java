package com.edu.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edu.school.service.UserService;
import com.edu.school.util.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/v1/auth/**").permitAll()
                        
                        .requestMatchers("api/v1/school/create").hasAnyAuthority(Role.ADMIN)
                        .requestMatchers("api/v1/school/update/**").hasAnyAuthority(Role.ADMIN)
                        .requestMatchers("api/v1/school/delete/**").hasAnyAuthority(Role.ADMIN)
                        .requestMatchers("api/v1/school/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/school/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/school/search/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/student/create").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/student/update/**").hasAnyAuthority(Role.MANAGER,Role.STUDENT)
                        .requestMatchers("api/v1/student/delete/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/student/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/student/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/student/search").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/studentcourse/create").hasAnyAuthority(Role.MANAGER,Role.STUDENT)
                        .requestMatchers("api/v1/studentcourse/update/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/studentcourse/delete/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/studentcourse/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/studentcourse/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/studentcourse/course/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/studentcourse/student/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/studentcourse/search").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        
                        .requestMatchers("api/v1/tutor/create").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutor/update/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutor/delete/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutor/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/tutor/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/tutor/search").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/tutorcourse/create").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutorcourse/update/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutorcourse/delete/**").hasAnyAuthority(Role.MANAGER)
                        .requestMatchers("api/v1/tutorcourse/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/tutorcourse/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/tutorcourse/tutor/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/tutorcourse/course/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/course/create/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER)
                        .requestMatchers("api/v1/course/update/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER)
                        .requestMatchers("api/v1/course/delete/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER)
                        .requestMatchers("api/v1/course/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/course/all/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/course/search").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/question/create/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/question/update/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/question/delete/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR)
                        .requestMatchers("api/v1/question/read/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        .requestMatchers("api/v1/question/all").hasAnyAuthority(Role.ADMIN,Role.MANAGER)
                        .requestMatchers("api/v1/question/testQuestion/**").hasAnyAuthority(Role.ADMIN,Role.MANAGER,Role.TUTOR,Role.STUDENT)
                        
                        .requestMatchers("api/v1/answer/**").hasAnyAuthority(Role.MANAGER,Role.TUTOR)
                        	
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
