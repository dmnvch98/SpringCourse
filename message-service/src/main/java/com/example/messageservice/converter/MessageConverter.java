package com.example.messageservice.converter;

import com.example.messageservice.dto.MessageDto;

import com.example.messageservice.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageConverter {
    MessageDto messageToDto(Message message);
}
