package ru.iflex.burov.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Calendar;

@JsonAutoDetect
public class Message {
    private int id;
    private String sender;
    private Calendar send_time;
    private String content;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Calendar getSend_time() {
        return send_time;
    }

    public void setSend_time(Calendar send_time) {
        this.send_time = send_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", send_time=" + send_time +
                ", content='" + content + '\'' +
                '}';
    }
}
