package com.example.messageservice.converter;

import com.example.messageservice.dto.MsgDto;

import com.example.messageservice.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageConverter {
    MsgDto messageToDto(Message message);
}
