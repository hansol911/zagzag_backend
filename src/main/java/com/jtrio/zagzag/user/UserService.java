package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.CheckEmailException;
import com.jtrio.zagzag.exception.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원조회
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return UserDTO.toDTO(user);
    }

    //회원가입
    public UserDTO createUser(UserCommand.CreateUser command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new CheckEmailException("ID duplication");
        }
        User user = userRepository.save(command.toUser());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserDTO.toDTO(user);
    }

    //회원정보수정
    public UserDTO updateUser(UserCommand.UpdateUser command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.save(command.toUser(user));
        return UserDTO.toDTO(user);
    }
}
