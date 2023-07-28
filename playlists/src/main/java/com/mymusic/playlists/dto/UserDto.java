package com.mymusic.playlists.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;
    private String email;


    public UserDto(String id, String email){
        setId(id);
        setEmail(email);
    }

    public UserDto(){}

}
