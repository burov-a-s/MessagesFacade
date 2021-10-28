package ru.iflex.burov.beans;

import ru.iflex.burov.Converter.MessageConverter;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.FacadeBean;
import ru.iflex.burov.lib.MyRestClient;
import ru.iflex.burov.soapClient.MySoapClient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@Stateless
public class FacadeBeanImpl implements FacadeBean {

    @EJB
    MyRestClient myRestClient;

    @EJB
    MySoapClient mySoapClient;

    @EJB
    MessageConverter converter;

    @Override
    public void addMessage(Message message) {
        myRestClient.addMessage(message);
    }

    @Override
    public void removeMessage(int id) {
        myRestClient.removeMessage(id);
    }

    @Override
    public List<Message> getMessagesByDate(XMLGregorianCalendar calendar) {
        List<ru.iflex.burov.messsage.Message> messages = mySoapClient.getMessageByDate(calendar).getMessages();
        return converter.convertManagerMessagesToEntityMessages(messages);
    }

    @Override
    public List<Message> getMessagesBySender(String sender) {
        List<ru.iflex.burov.messsage.Message> messages = mySoapClient.getMessageBySender(sender).getMessages();
        return converter.convertManagerMessagesToEntityMessages(messages);
    }

    @Override
    public List<Message> getAllMessages() {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("FacadeBeanImpl строка 51");
        return myRestClient.getAllMessages();
    }
}