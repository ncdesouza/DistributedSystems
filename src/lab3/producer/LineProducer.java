package lab3.producer;

import lab3.utility.FileIterator;
import lab3.utility.Line;
import lab3.utility.MessageQueue;

import java.util.Iterator;

/**
 * LineProducer:
 * <brief description of class>
 */
public class LineProducer implements Runnable {
    FileIterator input;
    MessageQueue<Line> q1;

    public LineProducer(FileIterator input, MessageQueue<Line> q1) {
        this.input = input;
        this.q1 = q1;
    }

    /** * puts strings from q1*/
    public void run() {
        try {
            for (Line anInput : input) {
                q1.put(anInput);
            }
            q1.put(new Line("END-OF-FILE", -1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}