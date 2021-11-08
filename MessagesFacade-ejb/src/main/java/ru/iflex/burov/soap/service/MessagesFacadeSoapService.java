package ru.iflex.burov.soap.service;

import ru.iflex.burov.converter.MessageConverter;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.facade.*;
import ru.iflex.burov.facade.ws.MessagesFacadePortType;
import ru.iflex.burov.interceptors.LoggerInterceptor;
import ru.iflex.burov.lib.FacadeBean;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@WebService(serviceName = "MessagesFacadeService",
        portName = "MessagesFacadePortType",
        endpointInterface = "ru.iflex.burov.facade.ws.MessagesFacadePortType",
        targetNamespace = "http://burov.iflex.ru/facade/ws")
@Stateless
@RolesAllowed("TestRole")
@Interceptors(LoggerInterceptor.class)
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
        List<Message> messages = facadeBean.getAllMessages();
        GetMessageResponse response = new GetMessageResponse();
        response.getMessages().addAll(converter.convertEntityMessagesToFacadeMessages(messages));
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