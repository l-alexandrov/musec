package musec.service;

import musec.entity.Role;
import musec.entity.User;

import java.util.HashSet;

public interface UserService {
    void save(User user);

    void save(User user, HashSet<Role> roles);

    User findByUsername(String username);
}
