package demo.securityapp.services;

import demo.securityapp.dto.request.CreateUserDTO;
import demo.securityapp.dto.response.UserResponseDto;
import demo.securityapp.models.RoleEntity;
import demo.securityapp.models.UserEntity;
import demo.securityapp.repository.RoleRepository;
import demo.securityapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class UserEntityServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Bring all the users
    public List<UserResponseDto> getAllUsers(){
        List<UserEntity>  users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponseDto( user.getUsername(), user.getRoles()))
                .collect(Collectors.toList());
    }


    //Bring a user by username
    public UserResponseDto getUserByUsername(String username){
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            return null;
        }
        return new UserResponseDto( user.getUsername(), user.getRoles());
    }


    public CreateUserDTO saveUser(CreateUserDTO createUserDTO){
        Set<RoleEntity> roles = createUserDTO.getRoles()
                .stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(RoleEntity.builder().name(roleName).build())))
                .collect(Collectors.toSet());
        UserEntity userEntity = UserEntity.builder()

                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(userEntity);
        return createUserDTO;
    }



    public UserEntity updateUser(String username, CreateUserDTO createUserDTO){
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if(user == null){
            return null;
        }
        user.setUsername(createUserDTO.getUsername());

        user.setPassword(createUserDTO.getPassword());
        Set<RoleEntity> roles = createUserDTO.getRoles()
                .stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseGet(() -> roleRepository.save(RoleEntity.builder().name(roleName).build())))
                .collect(Collectors.toSet());
        user.setRoles(roles);


        return userRepository.save(user);
    }

    public void deleteUser(String username){
        userRepository.findByUsername(username).ifPresent(user -> userRepository.delete(user));
    }

}
