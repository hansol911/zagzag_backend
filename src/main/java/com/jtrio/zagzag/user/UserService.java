package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.CheckEmailException;
import com.jtrio.zagzag.exception.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //모든회원조회
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    //회원가입
    public UserDTO signUp(UserCommand.CreateUser command) {
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new CheckEmailException("ID 중복");
        }

        User user = userRepository.save(command.toUser());
        return user.toDTO();
    }

    //회원정보수정
    public UserDTO updateUser(UserCommand.UpdateUser command, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("회원이 아님"));

        userRepository.save(command.toUser(user));
        return user.toDTO();
    }
}
