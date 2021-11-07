package ru.iflex.burov.rcp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iflex.burov.config.MessagesFacadeConfigHelper;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.MessagesFacadeRCPClient;
import ru.iflex.burov.rcp.RPCRequest;
import ru.iflex.burov.rcp.RPCResponse;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless
public class MessagesFacadeRPCClientImpl implements MessagesFacadeRCPClient {
    private int connectTimeOut = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRCPService().getConnectTimeOut();
    private int readTimeOut = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRCPService().getReadTimeOut();
    private String address = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRCPService().getEndpoint();

    @Override
    public List<Message> getAllMessages() {
        Client client = null;
        try {
            StringWriter writer = new StringWriter();
            RPCRequest request = new RPCRequest();
            request.setMethod("getAllMessages");
            request.setParams("someParams");

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, request);

            ClientBuilder builder = ClientBuilder.newBuilder();
            builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
            builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
            client = builder.newClient();

            URI uri = new URI(address);
            WebTarget target = client.target(uri);

            String jsonFromServer = target.request().post(Entity.json(writer.toString()), String.class);

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            mapper.setDateFormat(dateFormat);

            RPCResponse response = mapper.readValue(jsonFromServer, RPCResponse.class);
            List<Message> messages = response.getResult();

            return messages;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return null;
    }
}