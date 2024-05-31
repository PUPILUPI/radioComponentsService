package ru.belov.radioComponentsService.mapper;

import org.springframework.stereotype.Component;
import ru.belov.radioComponentsService.domain.dto.sql.RegDtoReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;

@Component
public class UserMapper {
    public MyUser toEntity(RegDtoReq req) {
        MyUser user = new MyUser();
        user.setUserRole(req.roleUser());
        user.setSurname(req.surname());
        user.setFirstName(req.firstName());
        user.setMiddleName(req.middleName());
        user.setEmail(req.email());
        user.setPassword(req.password());
        user.setPhoneNumber(req.phoneNumber());
        return user;
    }

    public RegDtoReq toDTO(MyUser user) {
        return new RegDtoReq(
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
