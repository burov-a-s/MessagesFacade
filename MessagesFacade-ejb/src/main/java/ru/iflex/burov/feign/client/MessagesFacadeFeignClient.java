package ru.iflex.burov.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import ru.iflex.burov.entity.Message;

import java.util.List;

public interface MessagesFacadeFeignClient {

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    public void addMessage(Message message);

    @RequestLine("DELETE /{id}")
    public void removeMessage(@Param("id") int id);

    @RequestLine("GET /")
    public List<Message> getAllMessages();
}

//tutorial лежит тут: https://github.com/OpenFeign/feign