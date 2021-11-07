package ru.iflex.burov.beans;

import ru.iflex.burov.converter.MessageConverter;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.FacadeBean;
import ru.iflex.burov.lib.MessagesFacadeRCPClient;
import ru.iflex.burov.lib.MessagesFacadeRestClient;
import ru.iflex.burov.soap.client.MessagesFacadeSoapClient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@Stateless

public class FacadeBeanImpl implements FacadeBean {

    @EJB
    private MessagesFacadeRestClient messagesFacadeRestClient;

    @EJB
    private MessagesFacadeRCPClient messagesFacadeRCPClient;

    @EJB
    private MessagesFacadeSoapClient messagesFacadeSoapClient;

    @EJB
    private MessageConverter converter;

    @Override
    public void addMessage(Message message) {
        messagesFacadeRestClient.addMessage(message);
    }

    @Override
    public void removeMessage(int id) {
        messagesFacadeRestClient.removeMessage(id);
    }

    @Override
    public List<Message> getMessagesByDate(XMLGregorianCalendar calendar) {
        List<ru.iflex.burov.messsage.Message> messages = messagesFacadeSoapClient.getMessageByDate(calendar).getMessages();
        return converter.convertManagerMessagesToEntityMessages(messages);
    }

    @Override
    public List<Message> getMessagesBySender(String sender) {
        List<ru.iflex.burov.messsage.Message> messages = messagesFacadeSoapClient.getMessageBySender(sender).getMessages();
        return converter.convertManagerMessagesToEntityMessages(messages);
    }

    @Override
    public List<Message> getAllMessages() {
//        return messagesFacadeRestClient.getAllMessages();
        return messagesFacadeRCPClient.getAllMessages();
    }
}