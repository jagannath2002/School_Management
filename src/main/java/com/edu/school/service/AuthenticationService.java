package com.edu.school.service;

import java.util.HashMap;
import java.util.regex.Pattern;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.school.DTO.JwtAuthenticationResponse;
import com.edu.school.DTO.LogInRequest;
import com.edu.school.DTO.RefreshTokenRequest;
import com.edu.school.DTO.SignUpRequest;
import com.edu.school.entity.User;
import com.edu.school.exception.CustomException;
import com.edu.school.exception.CustomException.ErrorType;
import com.edu.school.exception.EmailNotFoundException;
import com.edu.school.exception.InvalidCredentialsException;
import com.edu.school.repository.StudentRepository;
import com.edu.school.repository.TutorRepository;
import com.edu.school.repository.UserRepository;
import com.edu.school.util.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    
    public User studentSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }
    	if(!studentRepository.existsByEmail(signUpRequest.getEmail())) {
    		throw new CustomException("Email is not Allowed", ErrorType.INVALID_EMAIL);
    	}
    	
        User user =  new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.STUDENT);
        
        return userRepository.save(user);
    }
    
    public User tutorSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }
    	if(!tutorRepository.existsByEmail(signUpRequest.getEmail())) {
    		throw new CustomException("Email is not Allowed", ErrorType.INVALID_EMAIL);
    	}
    	
    	
        User user =  new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.TUTOR);
        return userRepository.save(user);
    }

    public User managerSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }
    	
        User user =  new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.MANAGER);
        return userRepository.save(user);
    }
    
    public User adminSignUp(SignUpRequest signUpRequest) {
    	
    	if(!(emailValidation(signUpRequest.getEmail()))) {
			throw new CustomException("email is not format",ErrorType.INVALID_EMAIL);
		}
		if(!(passwordValidation(signUpRequest.getPassword()))){
			throw new CustomException("password is not valid",ErrorType.INVALID_PASSWORD);
		}
    	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException("Email already exists", ErrorType.EMAIL_ALREADY_REGISTERED);
        }
    	
        User user =  new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }
    
    public JwtAuthenticationResponse signIn(LogInRequest logInRequest){
    	
        var user = userRepository.findByEmail(logInRequest.getEmail()).orElseThrow(() -> new EmailNotFoundException("Invalid email"));
        
        try {
        	 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getEmail(),
             		logInRequest.getPassword()));
        }
        catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid password");
        }
        
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse =
                new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);


            JwtAuthenticationResponse jwtAuthenticationResponse =
                    new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }
     
    public boolean emailValidation(String email) {
		return Pattern.compile("^[a-z0-9+_.-]+@(gmail|yahoo|outlook|zoho)\\.com$").matcher(email).matches();
	}
    
	private boolean passwordValidation(String password) {
		String pass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		return Pattern.compile(pass).matcher(password).matches();
	}
    
}
