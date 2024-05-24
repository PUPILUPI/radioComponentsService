package ru.belov.radioComponentsService.mapper;

import ru.belov.radioComponentsService.db.entity.EntityUser;
import ru.belov.radioComponentsService.dto.UserDTO;

public interface ConvertEntityUser {

     EntityUser toEntityUser(UserDTO userDTO);
}
