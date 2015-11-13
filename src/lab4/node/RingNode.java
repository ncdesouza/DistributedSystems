package lab4.node;

import lab4.messages.Message;
import lab4.node.Node;

/**
 * RingNode:
 * <brief description of class>
 */
public class RingNode extends Node implements Runnable {

    private volatile boolean running;

    /**
     * Default Constructor:
     * This id the default constructor of the Node class.
     * It will assign the node an ID and mark its state
     * as a Non-Participant.
     *
     * @param id An integer that represents the ID of the node.
     */
    public RingNode(int id) {
        super(id);
    }

    @Override
    public void run() {
        while (getState()) {

        }
    }
}
