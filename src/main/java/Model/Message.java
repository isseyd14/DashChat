// Message.java
package Model;

import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Message {
    private String content;
    private Timestamp timestamp;

    private String Sender;// Corrected field name to adhere to Java naming conventions



    public Message(String content, Timestamp timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(String content,String Sender, Timestamp timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return Sender;
    }




    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    public void setSender(String sender) {
        this.Sender = sender;
    }

}
