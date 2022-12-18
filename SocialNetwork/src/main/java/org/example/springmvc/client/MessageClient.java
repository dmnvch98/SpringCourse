package org.example.springmvc.client;

import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.model.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain", url = "${url}/api/v1/messages/")
public interface MessageClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{friendsId}")
    UserMessagesDto getUserMessages(final @PathVariable(name = "friendsId") long friendsId);

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserMessagesDto sendMessage(@RequestBody final Message message);
}
