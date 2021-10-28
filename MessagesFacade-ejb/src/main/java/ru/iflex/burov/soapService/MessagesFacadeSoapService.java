package ru.iflex.burov.soapService;

import ru.iflex.burov.Converter.MessageConverter;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.FacadeBean;
import ru.iflex.burov.facade.*;
import ru.iflex.burov.facade.ws.MessagesFacadePortType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.List;

@WebService(serviceName = "MessagesFacadeService",
        portName = "MessagesFacadePortType",
        endpointInterface = "ru.iflex.burov.facade.ws.MessagesFacadePortType",
        targetNamespace = "http://burov.iflex.ru/facade/ws")
@Stateless
public class MessagesFacadeSoapService implements MessagesFacadePortType {

    @EJB
    private FacadeBean facadeBean;

    @EJB
    private MessageConverter converter;

    @Override
    public GetMessageResponse getMessageByDate(GetMessageByDateRequest request) {
        if (request.getDate() != null) {
            XMLGregorianCalendar calendar = request.getDate();
            List<Message> messages = facadeBean.getMessagesByDate(calendar);
            GetMessageResponse response = new GetMessageResponse();
            response.getMessages().addAll(converter.convertEntityMessagesToFacadeMessages(messages));
            return response;
        } else {
            return null;
        }
    }

    @Override
    public GetMessageResponse getMessageBySender(GetMessageBySenderRequest request) {
        if (request.getSender() != null) {
            String sender = request.getSender();
            List<Message> messages = facadeBean.getMessagesBySender(sender);
            GetMessageResponse response = new GetMessageResponse();
            response.getMessages().addAll(converter.convertEntityMessagesToFacadeMessages(messages));
            return response;
        } else {
            return null;
        }
    }

    @Override
    public GetMessageResponse getAllMessages(GetAllMessagesRequest request) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 58");
        List<Message> messages = facadeBean.getAllMessages();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 61");
        GetMessageResponse response = new GetMessageResponse();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 64");
        List<ru.iflex.burov.facade.Message> messageList = converter.convertEntityMessagesToFacadeMessages(messages);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 67");
        List<ru.iflex.burov.facade.Message> entityMessages = response.getMessages();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 70");
        entityMessages.addAll(messageList);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessagesFacadeSoapService строка 73");
        return response;
    }

    @Override
    public AddMessageResponse addMessage(AddMessageRequest request) {
        if (request.getMessage() != null) {
            Message message = converter.convertFacadeMessageToEntityMessage(request.getMessage());
            facadeBean.addMessage(message);
            AddMessageResponse addMessageResponse = new AddMessageResponse();
            return addMessageResponse;
        } else {
            return null;
        }
    }

    @Override
    public RemoveMessageResponse removeMessage(RemoveMessageRequest request) {
        facadeBean.removeMessage(request.getId());
        RemoveMessageResponse removeMessageResponse = new RemoveMessageResponse();
        return removeMessageResponse;
    }
}