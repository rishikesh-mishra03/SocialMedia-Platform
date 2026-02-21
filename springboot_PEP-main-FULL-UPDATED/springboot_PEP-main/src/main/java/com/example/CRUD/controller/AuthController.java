package com.example.CRUD.controller;


import com.example.CRUD.auth.JwtUtil;
import com.example.CRUD.dto.LoginRequestDto;
import com.example.CRUD.entity.Token;
import com.example.CRUD.entity.User;
import com.example.CRUD.entity.VerficationStatus;
import com.example.CRUD.repository.TokenRepository;
import com.example.CRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request) {

        User user  = userRepository.findByUsername(request.getUsername());
        if (user.getPassword().equals(request.getPassword())) {

            String token = jwtUtil.generateToken(
                    request.getUsername(),
                    "USER"
            );

            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }


    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {

      Token t  = tokenRepository.findByToken(token) ;
      User user = t.getUser() ;
      user.getUserInfo().setVerificationStatus(VerficationStatus.VERIFIED);


      return ResponseEntity.ok("User is verified") ;

    }
}
