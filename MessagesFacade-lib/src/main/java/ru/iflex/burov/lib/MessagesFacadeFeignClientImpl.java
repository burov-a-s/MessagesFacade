package ru.iflex.burov.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import ru.iflex.burov.entity.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessagesFacadeFeignClientImpl {

    private static MessagesFacadeFeignClientImpl instance = new MessagesFacadeFeignClientImpl();

    public void addMessage(Message message) {
        buildClient().addMessage(message);
    }

    public void removeMessage(int id) {
        buildClient().removeMessage(id);
    }

    public List<Message> getAllMessages() {
        return buildClient().getAllMessages();
    }

    private MessagesFacadeFeignClient buildClient() {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        mapper.setDateFormat(dateFormat);
        MessagesFacadeFeignClient client = Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .encoder(new JacksonEncoder(mapper))
                .target(MessagesFacadeFeignClient.class, "http://127.0.0.1:7001/MessageManager-war/resources/RestController");
        return client;
    }

    public static MessagesFacadeFeignClientImpl getInstance() {
        return instance;
    }
}
