package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.db.entity.EntityUser;
import ru.belov.radioComponentsService.dto.UserDTO;
@Service
public class MapperEntityUser implements ConvertEntityUser{
    public EntityUser toEntityUser(UserDTO userDTO){
        EntityUser entityUser = new EntityUser();
        entityUser.setRoleUser(userDTO.roleUser());
        entityUser.setSurname(userDTO.surname());
        entityUser.setFirstName(userDTO.firstName());
        entityUser.setMiddleName(userDTO.middleName());
        entityUser.setEmail(userDTO.email());
        entityUser.setPassword(userDTO.password());
        entityUser.setPhoneNumber(userDTO.phoneNumber());
        return entityUser;
    }
}
