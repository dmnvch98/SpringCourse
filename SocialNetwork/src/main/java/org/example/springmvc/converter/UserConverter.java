package org.example.springmvc.converter;

import org.example.springmvc.dto.UserRestDto;
import org.example.springmvc.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {
    UserRestDto userToUserRestDto(User user);

    User userRestDtoToUser(UserRestDto userRestDto);
}
