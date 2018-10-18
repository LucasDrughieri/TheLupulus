package app.infraestructure;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

    public T data;
    public List<Message> messages;

    public Response(){
        this.messages = new ArrayList<>();
    }

    public void addSuccess(String message){
        this.messages.add(new Message(message, MessageType.Success));
    }

    public void addError(String message){
        this.messages.add(new Message(message, MessageType.Error));
    }

    public Boolean hasErrors(){
        return this.messages.stream().anyMatch(msg -> msg.type == MessageType.Error);
    }
}
