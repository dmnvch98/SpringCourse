package com.example.messageservice.converter;

import com.example.messageservice.dto.MessageDto;

import com.example.messageservice.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageConverter {
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "recipientId", source = "recipient.id")
    MessageDto messageToDto(Message message);
}
