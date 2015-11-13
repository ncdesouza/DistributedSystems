package lab4.node;

import lab4.messages.ElectedMessage;
import lab4.messages.ElectionMessage;
import lab4.messages.Message;

/**
 * Node:
 *      An object that represents a node in a circular network
 *      of nodes. Each node in the network is its own process.
 *      This class provides methods for retrieving its ID and
 *      the ID's of its neighbors. It also provides methods for
 *      receiving and sending messages.
 */
public class Node {

    private static final boolean PARTICIPANT = true;
    private static final boolean NONPARTICIPANT = false;

    /**
     * id: A integer that represent the id of this node.
     */
    private int id;

    /**
     * state: A boolean that represents the state of the Node.
     *        If state is true, the node is a participant. If
     *        state is false, the node is a non-participant.
     */
    private boolean state;

    /**
     * prev: An object of type Node that represents the node in
     *       the location before the current node in the circle.
     */
    private Node prev;

    /**
     * next: An object of type Node that represents the node in
     *       the location after the current node in the circle.
     */
    private Node next;

    /**
     * Default Constructor:
     *      This id the default constructor of the Node class.
     *      It will assign the node an ID and mark its state
     *      as a Non-Participant.
     * @param id An integer that represents the ID of the node.
     */
    public Node(int id) {
        this.id = id;
        this.state = NONPARTICIPANT;
    }

    /**
     * setPrev:
     *      This method is responsible for setting the neighbor
     *      node that is before this node in the circle network
     *      of nodes.
     * @param prev An object of type Node that represents the node
     *             that proceeds this node in the network.
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     * setNext:
     *      This method is responsible for setting the neighbor
     *      node that is after this node is the circular network
     *      of nodes.
     * @param next An object of type Node that represents the node
     *             that id positioned after this node in the network.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * getId:
     *      This method is responsible for getting the ID of this
     *      node.
     * @return An integer that represents the ID of this node.
     */
    public int getId() {
        return id;
    }


    public boolean getState() {
        return this.state;
    }


    public void startElection() {
        ElectionMessage msg = new ElectionMessage("started", this);
        this.state = PARTICIPANT;
        this.sendMsg(msg);
    }


    public void receiveMsg(Message msg) {
        System.out.println("id=" + this.getId() + ": received " + msg.getType() + " message");

        if (msg.getType().equals(ElectionMessage.ELECTION)) {

            if (msg.getSenderID() > this.getId()) {

                // forward msg
                msg.setContent("forwarding");
                this.state = PARTICIPANT;
                this.sendMsg(msg);

            } else if (msg.getSenderID() < this.getId()) {

                if (!state) {
                    msg.setSender(this);
                    msg.setContent("sending");
                    this.state = PARTICIPANT;
                    this.sendMsg(msg);
                }

            } else if (msg.getSenderID() == this.getId()) {

                Message electedMsg = new ElectedMessage("sending \"I am Elected\"", this);
                this.state = NONPARTICIPANT;
                this.sendMsg(electedMsg);

            }

        } else if (msg.getType().equals(ElectedMessage.ELECTED)) {

            if (state) {
                msg.setContent("forwarding \"Elected pid=" + msg.getSenderID() + "\"");
                this.state = NONPARTICIPANT;
                this.sendMsg(msg);
            }
        }

    }

    public void sendMsg(Message msg) {
        if (msg.getType().equals(ElectedMessage.ELECTED))
        System.out.println("id=" + this.getId() + ": " + msg.getContent() + " " + msg.getType() + " message.");
        this.next.receiveMsg(msg);
    }

}
