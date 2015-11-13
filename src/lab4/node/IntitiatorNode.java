package lab4.node;

/**
 * IntitiatorNode:
 * <brief description of class>
 */
public class IntitiatorNode extends RingNode {
    /**
     * Default Constructor:
     * This id the default constructor of the Node class.
     * It will assign the node an ID and mark its state
     * as a Non-Participant.
     *
     * @param id An integer that represents the ID of the node.
     */
    public IntitiatorNode(int id) {
        super(id);
    }

    @Override
    public void run() {
        startElection();
        super.run();
    }
}
