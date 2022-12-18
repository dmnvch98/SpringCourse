package org.example.springmvc.client;

import org.example.springmvc.client.dto.MessageDto;
import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.model.Friends;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "domain", url = "${url}/api/v1/messages/")
public interface MessageClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/{message}")
//    MessageDto getMessage(@PathVariable(name = "message") String message);

    @RequestMapping(method = RequestMethod.GET, value = "/{friendsId}")
    UserMessagesDto getUserMessages(@PathVariable(name = "friendsId") long friendsId);
}
