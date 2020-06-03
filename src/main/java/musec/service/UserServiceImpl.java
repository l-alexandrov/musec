package musec.service;

import musec.DTOs.UserDTO;
import musec.entity.RolesEnum;
import musec.entity.User;
import musec.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getFullName(), userDTO.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HashSet<RolesEnum> roles = new HashSet<>();
        roles.add(RolesEnum.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void save(UserDTO userDTO, HashSet<RolesEnum> roles) {
        User user = new User(userDTO.getUsername(), userDTO.getFullName(), userDTO.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
