package ru.iflex.burov.lib;

import ru.iflex.burov.entity.Message;

import java.util.List;

public interface MessagesFacadeRestClient {

    public void addMessage(Message message);

    public void removeMessage(int id);

    public List<Message> getAllMessages();

}
