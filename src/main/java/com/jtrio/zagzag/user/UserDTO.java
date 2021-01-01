package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String name;
    private Gender gender;
    private String addr;

    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setGender(user.getGender());
        userDTO.setAddr(user.getAddr());

        return userDTO;
    }
}
