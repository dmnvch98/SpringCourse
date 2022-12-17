package org.example.springmvc.client;

import org.example.springmvc.client.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "domain", url = "${services.message.url}/api/v1/messages")
public interface MessageClient {
    @RequestMapping(method = RequestMethod.GET)
    MessageDto getMessage(MessageDto message);
}
