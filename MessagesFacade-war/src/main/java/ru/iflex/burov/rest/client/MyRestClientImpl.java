package ru.iflex.burov.rest.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.MyRestClient;
import ru.iflex.burov.config.MyConfigHelper;

import javax.ejb.Stateless;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Stateless
public class MyRestClientImpl implements MyRestClient {
    private int connectTimeOut = MyConfigHelper.getInstance().getConfigurations().getConnectTimeOutRESTClient();
    private int readTimeOut = MyConfigHelper.getInstance().getConfigurations().getReadTimeOutRESTClient();
    private String address = MyConfigHelper.getInstance().getConfigurations().getMessageManagerRESTControllerAddress();

    @Override
    public void addMessage(Message message) {
        Client client = null;
        try {
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, message);
            ClientBuilder builder = ClientBuilder.newBuilder();
            builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
            builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
            client = builder.newClient();
            URI uri = new URI(address);
            WebTarget target = client.target(uri);
            Invocation invocation = target.request().buildPost(Entity.json(writer.toString()));
            Response response = invocation.invoke();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    @Override
    public void removeMessage(int id) {
        Client client = null;
        try {
            ClientBuilder builder = ClientBuilder.newBuilder();
            builder.connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS);
            builder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);
            client = builder.newClient();
            URI uri = new URI(address + "/" + id);
            WebTarget target = client.target(uri);
            Invocation invocation = target.request().buildDelete();
            Response response = invocation.invoke();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    @Override
    public List<Message> getAllMessages() {
        Client client = null;
        try {
            ClientBuilder builder = ClientBuilder.newBuilder();
            builder.connectTimeout(connectTimeOut, TimeUnit.SECONDS);
            builder.readTimeout(readTimeOut, TimeUnit.SECONDS);
            client = builder.newClient();
            URI uri = new URI(address);
            WebTarget target = client.target(uri);
            String jsonFromServer = target.request().get(String.class);
            StringReader reader = new StringReader(jsonFromServer);
            ObjectMapper mapper = new ObjectMapper();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            mapper.setDateFormat(dateFormat);
            List<Message> messages = mapper.readValue(reader, new TypeReference<List<Message>>() {
            });
            return messages;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}