package ru.iflex.burov.lib;

import feign.Param;
import feign.RequestLine;
import ru.iflex.burov.entity.Message;

import java.util.List;

public interface MessagesFacadeFeignClient {

    @RequestLine("POST /messages")
    public void addMessage(Message message);

    @RequestLine("DELETE /messages/{id}")
    public void removeMessage(@Param("id") int id);

    @RequestLine("GET /messages")
    public List<Message> getAllMessages();
}
