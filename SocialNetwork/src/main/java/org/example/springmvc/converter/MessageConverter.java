package org.example.springmvc.converter;

import org.example.springmvc.client.dto.SendMessageDto;
import org.example.springmvc.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring", uses = {RecipientIdConverter.class})
public interface MessageConverter {
    @Mapping(target = "recipient", source = "recipientId")
    Message dtoToMessage(SendMessageDto dto);
}
