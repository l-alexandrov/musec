package musec.service;

import musec.DTOs.UserDTO;
import musec.entity.RolesEnum;
import musec.entity.User;

import java.util.HashSet;

public interface UserService {
    void save(UserDTO user);

    void save(UserDTO user, HashSet<RolesEnum> roles);

    User findByUsername(String username);
}
