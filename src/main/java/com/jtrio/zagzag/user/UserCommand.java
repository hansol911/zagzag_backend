package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.UserRole;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        @NotBlank
        private String addr;
        @NotBlank
        private UserRole role;

        public User toUser() {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            user.setAddr(addr);
            user.setGender(gender);
            user.setRole(role);

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
