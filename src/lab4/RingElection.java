package lab4;

import lab4.node.IntitiatorNode;
import lab4.node.Node;
import lab4.node.RingNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * RingElection:
 * <brief description of class>
 */
public class RingElection {

    public static void main(String[] args) {

        int NUM_PROCCESSES = 5;

        int p_id = 25;
        List<RingNode> nodes = new ArrayList<RingNode>();
        nodes.add(new IntitiatorNode(p_id));
        for(int i = 1; i < NUM_PROCCESSES; i++) {
            nodes.add(new RingNode(p_id - 5*i));
            nodes.get(i-1).setNext(nodes.get(i));
        }
        nodes.get(nodes.size() - 1).setNext(nodes.get(0));

        for (RingNode node: nodes) {
            node.run();
        }

    }

}
