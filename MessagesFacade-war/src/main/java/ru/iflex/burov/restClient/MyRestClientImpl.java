package ru.iflex.burov.restClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.iflex.burov.entity.Message;
import ru.iflex.burov.lib.MyRestClient;

import javax.ejb.Stateless;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@Stateless
public class MyRestClientImpl implements MyRestClient {

    @Override
    public void addMessage(Message message) {
        Client client = null;
        try {
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, message);
            client = ClientBuilder.newClient();
            URI uri = new URI("http://127.0.0.1:7001/MessageManager-war/resources/RestController/messages");
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
            client = ClientBuilder.newClient();
            URI uri = new URI("http://127.0.0.1:7001/MessageManager-war/resources/RestController/messages/" + id);
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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://127.0.0.1:7001/MessageManager-war/resources/RestController/messages/");
        String jsonFromServer = target.request().get(String.class);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(jsonFromServer);
        System.out.println("--------------------------------------------------------------------------------------");
        StringReader reader = new StringReader(jsonFromServer);
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Message> messages = mapper.readValue(reader, List.class);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(messages);
            System.out.println("--------------------------------------------------------------------------------------");
            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
