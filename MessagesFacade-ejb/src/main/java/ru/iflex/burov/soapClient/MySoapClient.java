package ru.iflex.burov.soapClient;

import ru.iflex.burov.messsage.GetMessageByDateRequest;
import ru.iflex.burov.messsage.GetMessageBySenderRequest;
import ru.iflex.burov.messsage.GetMessageResponse;
import ru.iflex.burov.messsage.ws.MessageManagerPortType;
import ru.iflex.burov.messsage.ws.MessageManagerService;

import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;

@Stateless
public class MySoapClient {
    private static final MessageManagerService service = new MessageManagerService();
    private static final MessageManagerPortType portType = service.getMessageManagerService();

    private MessageManagerPortType getPort() {
        BindingProvider provider = (BindingProvider) portType;
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://127.0.0.1:7001/MySoapService/MessageManagerService");
        return portType;
    }

    public GetMessageResponse getMessageBySender(String sender) {
        GetMessageBySenderRequest request = new GetMessageBySenderRequest();
        request.setSender(sender);
        return getPort().getMessageBySender(request);
    }

    public GetMessageResponse getMessageByDate(XMLGregorianCalendar calendar) {
        GetMessageByDateRequest request = new GetMessageByDateRequest();
        request.setDate(calendar);
        return getPort().getMessageByDate(request);
    }
}
