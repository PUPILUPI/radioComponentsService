package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.UserDTO;
import ru.belov.radioComponentsService.domain.entity.sql.User;

@Component
public class UserMapper {
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserRole(userDTO.roleUser());
        user.setSurname(userDTO.surname());
        user.setFirstName(userDTO.firstName());
        user.setMiddleName(userDTO.middleName());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setPhoneNumber(userDTO.phoneNumber());
        return user;
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserRole(),
                user.getSurname(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getEmail(),
                null,
                user.getPhoneNumber()
        );
    }
}
