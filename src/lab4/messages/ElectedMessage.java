package lab4.messages;

import lab4.node.Node;

/**
 * ElectedMessage:
 * <brief description of class>
 */
public class ElectedMessage extends Message {

    public static final String ELECTED = "ELECTED";

    public ElectedMessage(String content, Node sender) {
        super(ELECTED, content, sender);
    }
}
