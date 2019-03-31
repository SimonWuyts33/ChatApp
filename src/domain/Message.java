package domain;

public class Message {
    private final String message;
    private final String sender;

    public Message(String sender, String message){
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
