package ru.iflex.burov.feign.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import ru.iflex.burov.config.MessagesFacadeConfigHelper;
import ru.iflex.burov.entity.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class MessagesFacadeFeignClientImpl {
    private int connectTimeOut = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRestService().getConnectTimeOut();
    private int readTimeOut = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRestService().getReadTimeOut();
    private String address = MessagesFacadeConfigHelper.getInstance().getConfigurations().getMessageManagerRestService().getEndpoint();

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
                .options(new Request.Options(connectTimeOut, MILLISECONDS, readTimeOut, MILLISECONDS, true))
                .target(MessagesFacadeFeignClient.class, address);

        return client;
    }

    public static MessagesFacadeFeignClientImpl getInstance() {
        return instance;
    }
}
