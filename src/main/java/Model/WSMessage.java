package Model;

public class WSMessage {

    private String userName;
    private String type;
    private String body;
    private String recipient;

    public WSMessage() {
    }

    public WSMessage(String userName, String type, String body, String recipient) {
        this.userName = userName;
        this.type = type;
        this.body = body;
        this.recipient = recipient;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}