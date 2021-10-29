package ru.iflex.burov.soap.client;

import ru.iflex.burov.config.MyConfigHelper;
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
    private int connectTimeOut = MyConfigHelper.getInstance().getConfigurations().getConnectTimeOutSOAPClient();
    private int readTimeOut = MyConfigHelper.getInstance().getConfigurations().getReadTimeOutSOAPClient();
    private String address = MyConfigHelper.getInstance().getConfigurations().getMessageManagerSOAPServiceAddress();
    private static final MessageManagerService service = new MessageManagerService();
    private static final MessageManagerPortType portType = service.getMessageManagerService();

    private MessageManagerPortType getPort() {
        BindingProvider provider = (BindingProvider) portType;
        provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
        provider.getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", connectTimeOut);
        provider.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", readTimeOut);
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