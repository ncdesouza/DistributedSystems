package lab4.messages;

import lab4.node.Node;

/**
 * ElectionMessage:
 * <brief description of class>
 */
public class ElectionMessage extends Message {

    public static final String ELECTION = "ELECTION";

    public ElectionMessage(String content, Node sender) {
        super(ELECTION, content, sender);
    }
}
