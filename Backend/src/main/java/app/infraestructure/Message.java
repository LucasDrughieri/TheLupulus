package app.infraestructure;

public class Message {

    public String text;
    public MessageType type;

    public Message(String text, MessageType type) {
        this.text = text;
        this.type = type;
    }
}
