package ru.iflex.burov.lib;

import ru.iflex.burov.entity.Message;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.List;

public interface FacadeBean {
    public void addMessage(Message message);
    public void removeMessage(int id);
    public List<Message> getMessagesByDate(XMLGregorianCalendar calendar);
    public List<Message> getMessagesBySender(String sender);
    public List<Message> getAllMessages();
}
