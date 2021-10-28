package ru.iflex.burov.Converter;

import ru.iflex.burov.entity.Message;

import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Stateless
public class MessageConverter {

    public List<ru.iflex.burov.facade.Message> convertManagerMessagesToFacadeMessages(List<ru.iflex.burov.messsage.Message> messagesIn) {
        if (messagesIn != null) {
                List<ru.iflex.burov.facade.Message> messagesOut = new ArrayList<>();
                messagesIn.forEach(message -> messagesOut.add(convertManagerMessageToFacadeMessage(message)));
                return messagesOut;
        } else {
            return null;
        }
    }

    public List<ru.iflex.burov.entity.Message> convertManagerMessagesToEntityMessages(List<ru.iflex.burov.messsage.Message> messagesIn) {
        if (messagesIn != null) {
            List<ru.iflex.burov.entity.Message> messagesOut = new ArrayList<>();
            messagesIn.forEach(message -> messagesOut.add(convertManagerMessageToEntityMessage(message)));
            return messagesOut;
        } else {
            return null;
        }
    }

    public List<ru.iflex.burov.messsage.Message> convertFacadeMessagesToManagerMessages(List<ru.iflex.burov.facade.Message> messagesIn) {
        if (messagesIn != null) {
            List<ru.iflex.burov.messsage.Message> messagesOut = new ArrayList<>();
            messagesIn.forEach(message -> messagesOut.add(convertFacadeMessageToManagerMessage(message)));
            return messagesOut;
        } else {
            return null;
        }
    }

    public List<ru.iflex.burov.entity.Message> convertFacadeMessagesToEntityMessages(List<ru.iflex.burov.facade.Message> messagesIn) {
        if (messagesIn != null) {
            List<ru.iflex.burov.entity.Message> messagesOut = new ArrayList<>();
            messagesIn.forEach(message -> messagesOut.add(convertFacadeMessageToEntityMessage(message)));
            return messagesOut;
        } else {
            return null;
        }
    }

    public List<ru.iflex.burov.facade.Message> convertEntityMessagesToFacadeMessages(List<ru.iflex.burov.entity.Message> messagesIn) {
        if (messagesIn != null) {
            System.out.println(messagesIn);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 60");
            List<ru.iflex.burov.facade.Message> messagesOut = new ArrayList<>();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 63");
            for (ru.iflex.burov.entity.Message message : messagesIn) {
                ru.iflex.burov.facade.Message newMessage = convertEntityMessageToFacadeMessage(message);
                messagesOut.add(newMessage);
            }
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 68");
            return messagesOut;
        } else {
            return null;
        }
    }

    public List<ru.iflex.burov.messsage.Message> convertEntityMessagesToManagerMessages(List<ru.iflex.burov.entity.Message> messagesIn) {
        if (messagesIn != null) {
            List<ru.iflex.burov.messsage.Message> messagesOut = new ArrayList<>();
            messagesIn.forEach(message -> messagesOut.add(convertEntityMessageToManagerMessage(message)));
            return messagesOut;
        } else {
            return null;
        }
    }

    public ru.iflex.burov.facade.Message convertManagerMessageToFacadeMessage(ru.iflex.burov.messsage.Message messageIn) {
        ru.iflex.burov.facade.Message messageOut = new ru.iflex.burov.facade.Message();
        messageOut.setId(messageIn.getId());
        messageOut.setSender(messageIn.getSender());
        messageOut.setSendTime(messageIn.getSendTime());
        messageOut.setContent(messageIn.getContent());
        return messageOut;
    }

    public ru.iflex.burov.entity.Message convertManagerMessageToEntityMessage(ru.iflex.burov.messsage.Message messageIn) {
        ru.iflex.burov.entity.Message messageOut = new ru.iflex.burov.entity.Message();
        messageOut.setId(messageIn.getId());
        messageOut.setSender(messageIn.getSender());

        XMLGregorianCalendar xmlGregorianCalendar = messageIn.getSendTime();
        Calendar calendar = xmlGregorianCalendar.toGregorianCalendar();

        messageOut.setSend_time(calendar);
        messageOut.setContent(messageIn.getContent());
        return messageOut;
    }

    public ru.iflex.burov.messsage.Message convertFacadeMessageToManagerMessage(ru.iflex.burov.facade.Message messageIn) {
        ru.iflex.burov.messsage.Message messageOut = new ru.iflex.burov.messsage.Message();
        messageOut.setId(messageIn.getId());
        messageOut.setSender(messageIn.getSender());
        messageOut.setSendTime(messageIn.getSendTime());
        messageOut.setContent(messageIn.getContent());
        return messageOut;
    }

    public ru.iflex.burov.entity.Message convertFacadeMessageToEntityMessage(ru.iflex.burov.facade.Message messageIn) {
        ru.iflex.burov.entity.Message messageOut = new ru.iflex.burov.entity.Message();
        messageOut.setId(messageIn.getId());
        messageOut.setSender(messageIn.getSender());

        XMLGregorianCalendar xmlGregorianCalendar = messageIn.getSendTime();
        Calendar calendar = xmlGregorianCalendar.toGregorianCalendar();

        messageOut.setSend_time(calendar);
        messageOut.setContent(messageIn.getContent());
        return messageOut;
    }

    public ru.iflex.burov.facade.Message convertEntityMessageToFacadeMessage(ru.iflex.burov.entity.Message messageIn) {
        ru.iflex.burov.facade.Message messageOut = new ru.iflex.burov.facade.Message();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessageConverter строка 132");
        try {
            messageOut.setId(messageIn.getId());
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 136");
            messageOut.setSender(messageIn.getSender());
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 139");
            Calendar calendarIn = messageIn.getSend_time();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 142");
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarIn.get(Calendar.YEAR),
                    calendarIn.get(Calendar.MONTH),
                    calendarIn.get(Calendar.DAY_OF_MONTH),
                    calendarIn.get(Calendar.HOUR),
                    calendarIn.get(Calendar.MINUTE),
                    calendarIn.get(Calendar.SECOND),
                    calendarIn.get(Calendar.MILLISECOND),
                    calendarIn.getTimeZone().getDSTSavings());
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 152");
            messageOut.setSendTime(xmlGregorianCalendar);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 155");
            messageOut.setContent(messageIn.getContent());
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("MessageConverter строка 158");
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("MessageConverter строка 163");
        return messageOut;
    }

    public ru.iflex.burov.messsage.Message convertEntityMessageToManagerMessage(ru.iflex.burov.entity.Message messageIn) {
        ru.iflex.burov.messsage.Message messageOut = new ru.iflex.burov.messsage.Message();
        try {
            messageOut.setId(messageIn.getId());
            messageOut.setSender(messageIn.getSender());
            Calendar calendarIn = messageIn.getSend_time();
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarIn.get(Calendar.YEAR),
                    calendarIn.get(Calendar.MONTH),
                    calendarIn.get(Calendar.DAY_OF_MONTH),
                    calendarIn.get(Calendar.HOUR),
                    calendarIn.get(Calendar.MINUTE),
                    calendarIn.get(Calendar.SECOND),
                    calendarIn.get(Calendar.MILLISECOND),
                    calendarIn.getTimeZone().getDSTSavings());
            messageOut.setSendTime(xmlGregorianCalendar);
            messageOut.setContent(messageIn.getContent());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return messageOut;
    }
}