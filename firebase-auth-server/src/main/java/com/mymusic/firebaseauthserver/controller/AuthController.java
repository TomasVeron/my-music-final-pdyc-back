package com.mymusic.firebaseauthserver.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.mymusic.firebaseauthserver.dto.UserDto;
import com.mymusic.firebaseauthserver.dto.ValidateTokenRequestDTO;
import com.mymusic.firebaseauthserver.dto.ValidateTokenResponseDTO;
import com.mymusic.firebaseauthserver.utils.FirebaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-firebase/auth")
public class AuthController {

    @Autowired
    FirebaseUtils firebaseUtils;
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody ValidateTokenRequestDTO request){
        try {
            FirebaseToken idToken = FirebaseAuth.getInstance().verifyIdToken(request.getToken());
            if (!(idToken == null)){
                return ResponseEntity.ok(new ValidateTokenResponseDTO(idToken.getUid(), idToken.getEmail(), idToken.getName(),request.getToken(), true));
            }
            return ResponseEntity.badRequest().body("This token is not valid");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
