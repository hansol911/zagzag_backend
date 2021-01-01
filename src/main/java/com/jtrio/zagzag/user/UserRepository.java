package com.jtrio.zagzag.user;

import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email); //boolean return
    User findByEmail(String email);
}
