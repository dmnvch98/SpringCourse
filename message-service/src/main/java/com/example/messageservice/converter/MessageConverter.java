package com.example.messageservice.converter;

import com.example.messageservice.dto.MessageDto;

import com.example.messageservice.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserServiceInterface.class })
public interface MessageConverter {
    @Mapping(target = "senderId", source = "sender")
    @Mapping(target = "recipientId", source = "recipient")
    MessageDto messageToDto(Message message);
}
