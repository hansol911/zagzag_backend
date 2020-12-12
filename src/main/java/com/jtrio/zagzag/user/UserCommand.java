package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserCommand {
    @Data
    public static class CreateUser {
        @Email
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String name;
        @NotBlank
        private Gender gender;
        private String addr;

        public User toUser() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setAddr(addr);
            user.setGender(gender);

            return user;
        }
    }

    @Data
    public static class UpdateUser {
        private String name;
        private String addr;

        public User toUser(User user) {
            user.setName(name);
            user.setAddr(addr);

            return user;
        }
    }
}
