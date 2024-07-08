package com.edu.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.school.DTO.JwtAuthenticationResponse;
import com.edu.school.DTO.LogInRequest;
import com.edu.school.DTO.RefreshTokenRequest;
import com.edu.school.DTO.SignUpRequest;
import com.edu.school.entity.User;
import com.edu.school.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/student-signup")
    public ResponseEntity<User> studentSignUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.studentSignUp(signUpRequest));
    }

    @PostMapping("/tutor-signup")
    public ResponseEntity<User> tutorSignUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.tutorSignUp(signUpRequest));
    }
    @PostMapping("/manager-signup")
    public ResponseEntity<User> managerSignUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.managerSignUp(signUpRequest));
    }
    @PostMapping("/admin-signup")
    public ResponseEntity<User> adminSignUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.adminSignUp(signUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LogInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}