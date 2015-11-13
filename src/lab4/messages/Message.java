package lab4.messages;

import lab4.node.Node;

/**
 * Message:
 * <brief description of class>
 */
public class Message {

    private String type;
    private String content;
    private Node sender;

    public Message(String type, String content, Node sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSenderID() {
        return sender.getId();
    }

    public void setSender(Node sender) {
        this.sender = sender;
    }
}
