package ru.iflex.burov.config;

import MessagesFacadeBinding.MessagesFacadeConfiguration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MyConfigHelper {
    private String fileName = System.getProperty("user.dir").
            concat(File.separator).
            concat("appConfig").
            concat(File.separator).
            concat("MessagesFacade.xml");
    private static MyConfigHelper instance = new MyConfigHelper();

    private static MessagesFacadeConfiguration messagesFacadeConfiguration;

    private MyConfigHelper() {
    }

    public static MyConfigHelper getInstance() {
        if (instance == null) {
            synchronized (MyConfigHelper.class) {
                if (instance == null) {
                    instance = new MyConfigHelper();
                }
            }
        }
        return instance;
    }

    public MessagesFacadeConfiguration getConfigurations() {
        if (messagesFacadeConfiguration == null) {
            synchronized (MyConfigHelper.class) {
                if (messagesFacadeConfiguration == null) {
                    messagesFacadeConfiguration = new MessagesFacadeConfiguration();
                    File file = new File(fileName);
                    try {
                        JAXBContext context = JAXBContext.newInstance(MessagesFacadeConfiguration.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        messagesFacadeConfiguration = (MessagesFacadeConfiguration) unmarshaller.unmarshal(file);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return messagesFacadeConfiguration;
    }
}