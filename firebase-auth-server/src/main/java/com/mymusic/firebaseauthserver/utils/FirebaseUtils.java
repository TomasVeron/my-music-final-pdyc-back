package com.mymusic.firebaseauthserver.utils;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;
import com.mymusic.firebaseauthserver.dto.UserDto;
import com.mymusic.firebaseauthserver.dto.ValidateTokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirebaseUtils {

    public UserRecord createUser(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest().setEmail(email).setPassword(password);
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        return userRecord;
    }


}
